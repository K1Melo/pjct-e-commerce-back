package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.jpa.repositories.ClientRepository;
import com.eccomerce.inter.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ManagementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Date creation;

    public String requestCode(String email) {
        User user = userRepository.findByEmail(email);
        user.setRecoveryCode(getRecoveryCode(user.getId()));
        user.setCodeValidityDate(new Date());
        userRepository.saveAndFlush(user);

        emailService.sendEmail(user.getEmail(), "Código de Recuperação de Senha", "O seu código para recuperação de senha é: " + user.getRecoveryCode());

        return "Código Enviado!";
    }

    public String changePassword(User user) {

        User databaseUser = userRepository.findByEmailAndRecoveryCode(user.getEmail(), user.getRecoveryCode());
        Date difference = new Date(new Date().getTime() - databaseUser.getCodeValidityDate().getTime());

        if ((difference.getTime()/1000) < 900) {
            databaseUser.setPassword(passwordEncoder.encode(user.getPassword()));
            databaseUser.setRecoveryCode(null);
            userRepository.saveAndFlush(databaseUser);
            return "Senha alterada com sucesso!";
        } else {
            return "Tempo expirado, solicite o código novamente";
        }
    }

    private String getRecoveryCode(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyy");
        return format.format(new Date())+id;
    }
}

package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.jpa.repositories.AddressRepository;
import com.eccomerce.inter.jpa.repositories.ClientRepository;
import com.eccomerce.inter.jpa.repositories.PermissionRepository;
import com.eccomerce.inter.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PermissionUserService permissionUserService;

    @Autowired
    private EmailService emailService;

    private Date creation;

    public User register(ClientRequestDTO clientRequestDTO) {
        User user = new ClientRequestDTO().converter(clientRequestDTO);
        user.setUpdateDate(new Date());
        user.setCreationDate(new Date());
        creation = user.getCreationDate();

        User newUser = clientRepository.saveAndFlush(user);
        permissionUserService.bindClientPermission(newUser);
//        emailService.sendEmail(newUser.getEmail(), "Cadastro na KumStore", "O registro foi realizado.");
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", newUser.getUsername());
        properties.put("message", "O seu cadastro na Kumstore foi realizado com sucesso. Por favor prossiga com o cadastro de sua senha");

        emailService.sendEmailTemplate(newUser.getEmail(), "Realização de cadastro na Kumstore", properties);

        return newUser;
    }


}

package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.jpa.repositories.AddressRepository;
import com.eccomerce.inter.jpa.repositories.ClientRepository;
import com.eccomerce.inter.jpa.repositories.PermissionRepository;
import com.eccomerce.inter.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PermissionUserService permissionUserService;

    @Autowired
    private EmailService emailService;

    private Date creation;

    public List<User> getAll() {
        return clientRepository.findAll();
    }

    public User register(ClientRequestDTO clientRequestDTO) {
        User user = new ClientRequestDTO().converter(clientRequestDTO);
        user.setUpdateDate(new Date());
        user.setCreationDate(new Date());
        creation = user.getCreationDate();

        User newUser = clientRepository.saveAndFlush(user);
        permissionUserService.bindClientPermission(newUser);
        emailService.sendEmail(newUser.getEmail(), "Cadastro na KumStore", "O registro foi realizado.");

        return newUser;
    }


}

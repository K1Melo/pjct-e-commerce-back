package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.domain.services.ClientService;
import com.eccomerce.inter.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientController {
    @Autowired
    private ClientService clientService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/")
    public User add(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.register(clientRequestDTO);
    }


}

package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.domain.services.UserServices;
import com.eccomerce.inter.resources.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sign")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<UserDTO> findAll() {
        return userServices.findAll();
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userServices.postUser(user);
    }
}

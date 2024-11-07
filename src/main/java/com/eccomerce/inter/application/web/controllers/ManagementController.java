package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.domain.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/management")
@CrossOrigin
public class ManagementController {
    @Autowired
    private ManagementService managementService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/recoveryCode")
    public String recoveryCode(@RequestBody User user) {
        return managementService.requestCode(user.getEmail());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody User user) {
        return managementService.changePassword(user);
    }
}

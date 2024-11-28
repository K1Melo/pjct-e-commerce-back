package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.dto.ClientRequestDTO;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.domain.security.JwtUtil;
import com.eccomerce.inter.domain.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/management")
@CrossOrigin
public class ManagementController {
    @Autowired
    private ManagementService managementService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User authenticatedUser = (User) authentication.getPrincipal();
        String token = jwtUtil.usernameTokenGenerator(authenticatedUser);

        return ResponseEntity.ok(token);
    }
}

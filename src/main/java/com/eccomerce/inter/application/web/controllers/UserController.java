package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/")
    public User change(@RequestBody User user) {
        return userService.change(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        userService.del(id);
        return ResponseEntity.ok().build();
    }
}

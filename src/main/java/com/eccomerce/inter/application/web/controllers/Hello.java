package com.eccomerce.inter.application.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/hello")
public class Hello {
    @GetMapping
    public String hello() {
        return "Hello, World!! " + new Date();
    }
}

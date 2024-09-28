package com.eccomerce.inter.application.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    private String getMapping() { return "Salve Araujo"; }
}

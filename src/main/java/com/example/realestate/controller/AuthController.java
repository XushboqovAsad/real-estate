package com.example.realestate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/api/auth/success")
    public String loginSuccess() {
        return "Google login successful!";
    }
}

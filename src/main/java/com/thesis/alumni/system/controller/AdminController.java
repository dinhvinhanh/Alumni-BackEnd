package com.thesis.alumni.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    @GetMapping
    public String testAuth() {
        // dung de check quyen trong jwt
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "access";
    }
}

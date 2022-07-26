package com.thesis.alumni.system.controller;

import lombok.AllArgsConstructor;
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
        return "access";
    }
}

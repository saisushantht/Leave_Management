package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)

public class TestController {
	@Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/all")
    public List<String> allAccess() {
        return List.of("public content");
    }

}

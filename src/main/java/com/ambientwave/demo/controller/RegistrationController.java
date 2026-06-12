package com.ambientwave.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambientwave.demo.models.ResUser;
import com.ambientwave.demo.repository.ResUserRepository;
import com.ambientwave.demo.services.ResUserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private ResUserService resUserService;

    @Autowired
    private ResUserRepository resUserRepository; // dependency injection to access the database-related methods

    @Autowired
    private PasswordEncoder passwordEncoder; // dependency injection to access the password encoder

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public ResUser createUser(@Valid @RequestBody final ResUser user) { // Without @RequestBody, Spring MVC
                                                                 // tries to bind ResUser from form fields
                                                                 // (URL-encoded form data), not from the JSON body
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return resUserRepository.save(user); // to save the user
        // return "redirect:/login"; // to redirect to the login page
    }

}

package com.carlosandrade.microservice.auth.controllers;

import com.carlosandrade.microservice.auth.dto.LoginRequestModel;
import com.carlosandrade.microservice.auth.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestModel userLogin) {

        UserDetails userAuthenticated = userService.loadUserByUsername(userLogin.getEmail());

        return ResponseEntity.ok(userAuthenticated);
    }


}

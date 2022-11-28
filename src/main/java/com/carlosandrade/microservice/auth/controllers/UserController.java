package com.carlosandrade.microservice.auth.controllers;

import com.carlosandrade.microservice.auth.model.UserEntity;
import com.carlosandrade.microservice.auth.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserEntity userEntity) {

        UserEntity userCreated = userService.createUser(userEntity);

        return ResponseEntity.ok(userCreated);
    }

}

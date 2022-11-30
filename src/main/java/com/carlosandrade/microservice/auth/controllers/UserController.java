package com.carlosandrade.microservice.auth.controllers;

import com.carlosandrade.microservice.auth.model.UserEntity;
import com.carlosandrade.microservice.auth.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

   /* @GetMapping
    public Principal user(Principal user) {
        return user;
    }*/

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserEntity userEntity) {

        UserEntity userCreated = null;
        try {
            userCreated = userService.createUser(userEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(userCreated);
    }

    @DeleteMapping
    public ResponseEntity delete (Long id){

        try {
            userService.delete(id);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}

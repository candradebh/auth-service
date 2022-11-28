package com.carlosandrade.microservice.auth.services;

import com.carlosandrade.microservice.auth.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    UserEntity createUser(UserEntity userEntity);
}

package com.carlosandrade.microservice.auth.services;


import com.carlosandrade.microservice.auth.dto.UserEntityDto;
import com.carlosandrade.microservice.auth.model.UserEntity;
import com.carlosandrade.microservice.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity createUser(UserEntity userEntity) throws Exception {
        Optional<UserEntity> user = userRepository.findByEmail(userEntity.getEmail());

        if(user.isPresent()){
            throw new Exception("O usuário com email " + userEntity.getEmail() + " ja esta cadastrado");
        }

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserDetailsByEmail(String username) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(username);

        return userOptional.get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username);

        if (!userEntityOptional.isPresent()) throw new UsernameNotFoundException(username);

        UserEntity userEntity = userEntityOptional.get();

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());

    }

    public Iterable<UserEntity> allUsers() {
        Iterable<UserEntity> users = userRepository.findAll();
        return users;
    }

    public void delete(Long id) throws Exception {

        Optional<UserEntity> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) throw new Exception("O usuário não existe.");

        userRepository.delete(userOptional.get());

    }
}

package com.carlosandrade.microservice.auth.repositories;

import com.carlosandrade.microservice.auth.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
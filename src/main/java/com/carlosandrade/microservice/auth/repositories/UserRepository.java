package com.carlosandrade.microservice.auth.repositories;

import com.carlosandrade.microservice.auth.dto.UserEntityDto;
import com.carlosandrade.microservice.auth.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
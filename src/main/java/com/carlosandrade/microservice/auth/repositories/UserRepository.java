package com.carlosandrade.microservice.auth.repositories;

import com.carlosandrade.microservice.auth.dto.UserEntityDto;
import com.carlosandrade.microservice.auth.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntityDto findByEmail(String email);

}
package com.carlosandrade.microservice.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carlosandrade.microservice.auth.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
}

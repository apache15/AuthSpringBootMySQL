package com.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auth.model.DAOUser;


public interface UserRepository extends CrudRepository<DAOUser, Integer>{

	DAOUser findByUsername(String username);
}

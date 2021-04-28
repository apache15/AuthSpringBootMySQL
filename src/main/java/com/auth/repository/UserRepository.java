package com.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.auth.model.DAOUser;


public interface UserRepository extends CrudRepository<DAOUser, Integer>{

	@Query(value = "select * from users where username = :username", nativeQuery = true)
	DAOUser findByUsername(@Param(value = "username") String username);
}

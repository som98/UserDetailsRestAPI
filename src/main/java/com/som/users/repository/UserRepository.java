package com.som.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.users.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	

}

package com.som.users.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.som.users.exception.UserNotFoundException;
import com.som.users.model.User;
import com.som.users.service.UserDAOService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserDAOService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUserById(@PathVariable int id){
		User user = service.findById(id);
		
		if (user==null) {
			throw new UserNotFoundException("id:" +id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();			
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id){
		service.deleteById(id);
		
	}
	

}

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
import com.som.users.model.Post;
import com.som.users.model.User;
import com.som.users.repository.PostRepository;
import com.som.users.repository.UserRepository;
import com.som.users.service.UserDAOService;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PostRepository postrepo;
	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repo.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUserById(@PathVariable int id){
		User user = repo.findById(id).get();
		
		if (user==null) {
			throw new UserNotFoundException("id:" +id);
		}
		return user;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repo.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();			
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id){
		repo.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsForaUser(@PathVariable int id){

		User user = repo.findById(id).get();
		
		if (user==null) {
			throw new UserNotFoundException("id:" +id);
		}
		System.out.println(user);
		return user.getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForaUser(@PathVariable int id, @Valid @RequestBody Post post){

		User user = repo.findById(id).get();
		
		if (user==null) {
			throw new UserNotFoundException("id:" +id);
		}
		
		post.setUser(user);
		
		Post savedPost = postrepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}

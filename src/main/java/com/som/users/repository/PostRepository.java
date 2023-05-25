package com.som.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.som.users.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}

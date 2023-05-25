package com.som.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class UserDetailsRestAPI {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailsRestAPI.class, args);
	}

}

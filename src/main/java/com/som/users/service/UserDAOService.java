package com.som.users.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.som.users.model.User;

@Service
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	
	private static int usersCount=0;
	
	static {
		users.add(new User(++usersCount,"Som",LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Sam",LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount,"Ravi",LocalDate.now().minusYears(15)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findById(int id){
		return users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id){
		users.removeIf(user->user.getId().equals(id));
	}
	
	
}

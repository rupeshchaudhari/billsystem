package com.setu.billsystem.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.setu.billsystem.model.User;

@Component
public class UserRepository {
	
	List<User> users = new ArrayList<User>();
	
	public UserRepository() {
		users.add(new User("user","user"));
	}
	
	public User findUserBySchemeId(String schemeId){
		for (User user : users) {
			if(user.getSchemeId().equals(schemeId)) {
				return user;
			}
		}
		return null;
	}
	
	
}
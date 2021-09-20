package com.example.newshop.service;

import com.example.newshop.model.User;

import java.util.List;

public interface UserService {
	
	public User findByEmail(String email);
	
	public void save(User user);
	
	public void update(User user);
	
	public List<User> findAllUser();
	
	public void deleteUser(long userId);
}

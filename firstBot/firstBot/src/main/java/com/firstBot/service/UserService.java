package com.firstBot.service;

import java.util.List;

import com.firstBot.entity.User;

public interface UserService {

	void save(User user);

	List<User> findAll();

	User findOne(int id);

	boolean remove(int id);

	User findByMessengerUserId(String messengerUserId);
	
	String getUserUrl(User user);
	
	void removeGenresAndYears(User user);
	
	void addGenreToUser(User user, String incomePayload);
	
	void setUserYears(User user, String incomePayload);
}

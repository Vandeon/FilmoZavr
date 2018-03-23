package com.firstBot.service;

import java.util.List;

import com.firstBot.entity.User;
import com.firstBot.model.other.UserStatus;

public interface UserService {

	void save(User user);

	List<User> findAll();

	User findOne(int id);

	boolean remove(int id);

	User findByMessengerUserId(String messengerUserId);
	
	String getUserUrl(User user);
	
	void removeGenresAndYears(User user);
	
	void addGenreToUser(User user, String incomePayload);
	
	void addGenreToUser(User user, List<String> incomePayload);
	
	void setUserYears(User user, String incomePayload);
	
	void setUserStatus(User user, UserStatus userStatus);
	
	void setCommentingFilmId(User user, String commentingFilmId);

	void removeCommentingFilmId(User user);
}

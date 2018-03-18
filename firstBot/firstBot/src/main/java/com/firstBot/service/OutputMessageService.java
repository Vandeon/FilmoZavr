package com.firstBot.service;

import com.firstBot.entity.User;

public interface OutputMessageService {

	public void sendTextMessage(User user, String text);
	
	public void sendGreetings(User user);
	
	public void offerGenres(User user);
	
	public void offerFilms(User user);
	
	public void offerYears(User user);
}

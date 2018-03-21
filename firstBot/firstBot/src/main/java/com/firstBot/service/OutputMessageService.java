package com.firstBot.service;

import com.firstBot.entity.User;

import ai.api.model.AIResponse;

public interface OutputMessageService {

	public void sendTextMessage(User user, String text);
	
	public void sendGreetings(User user);
	
	public void offerGenres(User user);
	
	public void offerFilms(User user);
	
	public void offerYears(User user);
	
	public AIResponse sendToDialogflow(String text);

	void showComments(User user, String FilmId);

	void showOneComment(User user, String incomePayload);
	
	public void offerRate(User user);

	public void sendCommentQuickReply(User user, String filmId);
	
}

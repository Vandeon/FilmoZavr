package com.firstBot.service;

import com.firstBot.entity.User;
import com.firstBot.model.incomeMessaging.IncommingMessage;

public interface SimpleMessageService {

	void introduse(User user, IncommingMessage message);
	
}

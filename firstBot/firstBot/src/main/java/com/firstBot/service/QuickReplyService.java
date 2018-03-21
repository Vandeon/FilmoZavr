package com.firstBot.service;

import com.firstBot.entity.User;

public interface QuickReplyService {

	void doIt(User user, String incomePayload);
	
}

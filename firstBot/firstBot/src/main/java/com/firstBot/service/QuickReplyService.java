package com.firstBot.service;

import com.firstBot.entity.User;
import com.firstBot.model.outputMessaging.QuickReply;

public interface QuickReplyService {

	void doIt(User user, QuickReply quickReply);
	
}

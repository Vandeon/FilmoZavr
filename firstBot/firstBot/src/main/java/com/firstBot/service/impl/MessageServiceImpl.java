package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.User;
import com.firstBot.model.incomeMessaging.Entry;
import com.firstBot.model.incomeMessaging.IncommingMessage;
import com.firstBot.model.incomeMessaging.MessagingIn;
import com.firstBot.model.other.UserInfo;
import com.firstBot.model.outputMessaging.QuickReply;
import com.firstBot.service.MessageService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.TextMessageService;
import com.firstBot.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private UserService userService;

	@Autowired
	private QuickReplyService quickReplyService;
	
	@Autowired
	private TextMessageService textMessageService;
	
	@Value("${access}")
	String access;
	
	@Value("${url.bot}")
	String urlbot;
	
	@Value("${url.facebook}")
	String urlFacebook;
	
	@Value("${url.getusername}")
	String urlUserName;
	
	@Value("${secret}")
	String secret;

	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${qr.cancel.payload}")
	String cancelPayload;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${qr.done.payload}")
	String donePayload;
	
	public void getMessage(IncommingMessage message) {
		List<Entry> entryList = message.getEntry();
		for (int i = 0; i < entryList.size(); i++) {
			List<MessagingIn> messagingList = entryList.get(i).getMessaging();
			for (int j = 0; j < messagingList.size(); j++) {
				
				QuickReply quickReply = messagingList.get(j).getMessage().getQuick_reply();
				String messengerUserId = messagingList.get(j).getSender().getId();

				UserInfo userInfo = getUserInfo(messengerUserId);
				User user = getUser(userInfo);
				
				if (quickReply != null) {
					quickReplyService.doIt(user, quickReply);
				} else {
					String messageText = messagingList.get(j).getMessage().getText();
					textMessageService.doIt(user, messageText);
				}
			}
		}

	}

	private User getUser(UserInfo userInfo) {
		String messengerUserId = userInfo.getId();
		User user = userService.findByMessengerUserId(messengerUserId);
		if (user == null) {
			user = new User();
			user.setMessengerUserId(messengerUserId);
			user.setFirstName(userInfo.getFirst_name());
			user.setLastName(userInfo.getLast_name());
			userService.save(user);
		}
		return user;
	}

	private UserInfo getUserInfo(String messengerUserId) {
		RestTemplate rt = new RestTemplate();
		UserInfo userInfo = rt.getForObject(urlFacebook+messengerUserId+urlUserName+access, UserInfo.class);
		return userInfo;
	}

}

// SYSO JSON
// ObjectMapper om = new ObjectMapper();
// try {
// System.out.println(om.writeValueAsString(template));
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }

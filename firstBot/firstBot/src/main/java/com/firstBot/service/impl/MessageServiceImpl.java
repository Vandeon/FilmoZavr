package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.User;
import com.firstBot.model.incomeMessaging.Entry;
import com.firstBot.model.incomeMessaging.IncommingMessage;
import com.firstBot.model.incomeMessaging.MessageIn;
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

	@Async
	public void getMessage(IncommingMessage message) {
		List<Entry> entryList = message.getEntry();
		for (int i = 0; i < entryList.size(); i++) {
			List<MessagingIn> messagingList = entryList.get(i).getMessaging();
			for (int j = 0; j < messagingList.size(); j++) {
				MessagingIn messaging = messagingList.get(j);
				
				String messengerUserId = messaging.getSender().getId();
				UserInfo userInfo = getUserInfo(messengerUserId);
				User user = getUser(userInfo);
				
				String payload;
				if (messaging.getMessage() != null) {
					MessageIn messageIn = messaging.getMessage();
					QuickReply quickReply = messageIn.getQuick_reply();
					if (quickReply != null) {
						payload = quickReply.getPayload();
						quickReplyService.doIt(user, payload);
					}else {
						String messageText = messagingList.get(j).getMessage().getText();
						textMessageService.doIt(user, messageText);
					}
				}else {
					payload = messaging.getPostback().getPayload();
					quickReplyService.doIt(user, payload);
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
			user.setProfile_pic(userInfo.getProfile_pic());
			userService.save(user);
		}
		return user;
	}

	private UserInfo getUserInfo(String messengerUserId) {
		RestTemplate rt = new RestTemplate();
		UserInfo userInfo = rt.getForObject(urlFacebook + messengerUserId + urlUserName + access, UserInfo.class);
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

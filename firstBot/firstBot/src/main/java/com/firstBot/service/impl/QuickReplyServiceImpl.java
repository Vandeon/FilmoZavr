package com.firstBot.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.firstBot.entity.User;
import com.firstBot.model.outputMessaging.QuickReply;
import com.firstBot.service.OutputMessageService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.UserService;

@Service
public class QuickReplyServiceImpl implements QuickReplyService{

	@Autowired
	private UserService userService; 
	
	@Autowired 
	private OutputMessageService outputMessageService;

	@Value("${access}")
	String access;
	
	@Value("${url.bot}")
	String urlbot;
	
	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${qr.cancel.payload}")
	String cancelPayload;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${qr.done.payload}")
	String donePayload;

	@Value("${qr.back.title}")
	String backTitle;

	@Value("${qr.back.payload}")
	String backPayload;

	@Value("${trailer}")
	String trailer;
	
	@Value("${yearCases}")
	String yearCases;
	
	@Value("${yearsInterval}")
	String yearsInterval;
	
	@Override
	public void doIt(User user, QuickReply quickReply) {
		
		String incomePayload = quickReply.getPayload();
		
		if (incomePayload.equals(donePayload)) {
			outputMessageService.offerYears(user);
		}else if (incomePayload.equals(cancelPayload)) {
			userService.removeGenresAndYears(user);
			outputMessageService.offerGenres(user);
		}else if(incomePayload.equals(backPayload)){
			outputMessageService.offerGenres(user);
		}else if(ifYears(incomePayload)) {
			userService.setUserYears(user, incomePayload);
			outputMessageService.offerFilms(user);
		}else {
			userService.addGenreToUser(user, incomePayload);
			outputMessageService.offerGenres(user);
		}
	}
	
	 private boolean ifYears(String testString){  
	        Pattern p = Pattern.compile("^\\d\\d\\d\\d\\D\\d\\d\\d\\d$");  
	        Matcher m = p.matcher(testString);  
	        return m.matches();  
	    } 
	 
}

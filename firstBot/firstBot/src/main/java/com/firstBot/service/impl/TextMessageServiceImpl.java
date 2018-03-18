package com.firstBot.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.service.GenreService;
import com.firstBot.service.OutputMessageService;
import com.firstBot.service.TextMessageService;
import com.firstBot.service.UserService;

@Service
public class TextMessageServiceImpl implements TextMessageService {

	@Autowired
	private OutputMessageService outputMessageService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private UserService userService;
	
	@Override
	public void doIt(User user, String messageText) {
		if (messageText.equals("test")) {

		} else if (ifYears(messageText)) {
			userService.setUserYears(user, messageText);
			outputMessageService.offerFilms(user);
		} else if (ifGenre(messageText)) {
			Genre genre = genreService.findByName(messageText);
			if (user.getGenres().contains(genre)) {
				outputMessageService.sendTextMessage(user, messageText + " was already choosed, may be wanna chose another one?");
			} else {
				messageText = messageText.substring(0, 1).toUpperCase() + messageText.substring(1);
				userService.addGenreToUser(user, ""+genreService.findByName(messageText).getId());
				outputMessageService.sendTextMessage(user, messageText + " was added to your genres. Wanna chose some more?");
			}
			outputMessageService.offerGenres(user);
		} else {
			outputMessageService.sendGreetings(user);
			outputMessageService.offerGenres(user);
		}
	}

	private boolean ifYears(String testString) {
		Pattern p = Pattern.compile("^\\d\\d\\d\\d\\D\\d\\d\\d\\d$");
		Matcher m = p.matcher(testString);
		return m.matches();
	}

	private boolean ifGenre(String testString) {
		List<Genre> genres = genreService.findAll();
		for (int i = 0; i < genres.size(); i++) {
			if (genres.get(i).getName().equalsIgnoreCase(testString)) {
				return true;
			}
		}
		return false;
	}

}

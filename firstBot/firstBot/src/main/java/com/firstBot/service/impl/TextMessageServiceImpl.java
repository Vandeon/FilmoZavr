package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Comment;
import com.firstBot.entity.User;
import com.firstBot.model.other.UserStatus;
import com.firstBot.service.CommentService;
import com.firstBot.service.FilmService;
import com.firstBot.service.OutputMessageService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.TextMessageService;
import com.firstBot.service.UserService;
import com.google.gson.JsonElement;

import ai.api.model.AIResponse;

@Service
public class TextMessageServiceImpl implements TextMessageService {

	@Autowired
	private OutputMessageService outputMessageService;

	@Autowired
	private QuickReplyService quickReplyService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FilmService filmService;

	@Value("${offer_genres}")
	String offerGenres;

	@Value("${genre}")
	String genre;

	@Value("${thank_you}")
	String thankYou;

	@Override
	public void doIt(User user, String messageText) {

		if (user.getUserStatus().equals(UserStatus.WRITING_COMMENT)) {
			addComment(user, messageText);
		} else if (ifYears(messageText)) {
			userService.setUserYears(user, messageText);
			outputMessageService.offerFilms(user);
		} else {
			dialogFlowDoIt(user, messageText);
		}
	}

	private void addComment(User user, String messageText) {
		commentService.addComment(
				new Comment(user, filmService.findOne(Integer.parseInt(user.getCommentingFilmId())), messageText));
		outputMessageService.sendTextMessage(user, thankYou);
		userService.setUserStatus(user, UserStatus.CHOOSING_GENRES);
		userService.removeCommentingFilmId(user);
	}

	private boolean ifYears(String testString) {
		Pattern p = Pattern.compile("^\\d\\d\\d\\d\\D\\d\\d\\d\\d$");
		Matcher m = p.matcher(testString);
		return m.matches();
	}
	
	private void dialogFlowDoIt(User user, String messageText) {
		AIResponse response = outputMessageService.sendToDialogflow(messageText);
		String outputText = response.getResult().getFulfillment().getSpeech();
		String intent = response.getResult().getMetadata().getIntentName();
		if (intent.equals(offerGenres)) {
			addGenresToUserAndOfferGenres(user, response);
		}
		sendTextFromDialogFlowToUser(user, outputText);
		quickReplyService.doIt(user, intent);
	}

	private void addGenresToUserAndOfferGenres(User user, AIResponse response) {
		HashMap<String, JsonElement> map = response.getResult().getParameters();
		JsonElement genres = map.get(genre);
		Iterator<JsonElement> iterator = genres.getAsJsonArray().iterator();
		List<String> genreList = new ArrayList<String>();
		while (iterator.hasNext()) {
			genreList.add(iterator.next().getAsString());
		}
		userService.addGenreToUser(user, genreList);
	}
	
	private void sendTextFromDialogFlowToUser(User user, String outputText) {
		if (!outputText.equals("")) {
			outputMessageService.sendTextMessage(user, outputText);
		}
	}

}

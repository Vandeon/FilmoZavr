package com.firstBot.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.firstBot.entity.User;
import com.firstBot.model.other.UserStatus;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.OutputMessageService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.RateService;
import com.firstBot.service.UserService;

@Service
public class QuickReplyServiceImpl implements QuickReplyService {

	@Autowired
	private UserService userService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private OutputMessageService outputMessageService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private RateService rateService;

	@Value("${access}")
	String access;

	@Value("${url.bot}")
	String urlbot;

	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${remove_years_and_genres}")
	String cancel;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${offer_years}")
	String offerYears;

	@Value("${qr.back.title}")
	String backTitle;

	@Value("${offer_genres}")
	String offerGenres;

	@Value("${trailer}")
	String trailer;

	@Value("${yearCases}")
	String yearCases;

	@Value("${yearsInterval}")
	String yearsInterval;

	@Value("${add_comment}")
	String addComment;

	@Value("${greetings}")
	String greetings;

	@Value("${comment_pls}")
	String commentPls;

	@Value("${button.comment.add.payload}")
	String addCommentPayload;

	@Value("${button.view.film.comments.payload}")
	String viewFilmCommentsPayload;

	@Value("${button.view.comment.payload}")
	String viewCommentPayload;
	
	@Value("${button.rate.payload}")
	String rateFilmPayload;
	
	@Value("${thank_you}")
	String thankYou;
	
	@Value("${button.comment.payload}")
	String commentPayload;
	
	@Override
	public void doIt(User user, String incomePayload) {
		if(user.getUserStatus().equals(UserStatus.RAITING_FILM)) {
			rateService.addOrUpdateRate(Integer.parseInt(incomePayload), user, filmService.findOne(Integer.parseInt(user.getCommentingFilmId())));
			userService.setUserStatus(user, UserStatus.NEW);
			outputMessageService.sendTextMessage(user, thankYou);
		}else {
		if (incomePayload.equals(greetings)) {
//			outputMessageService.offerRate(user);
			outputMessageService.sendGreetings(user);
			outputMessageService.offerGenres(user);
		} else if (incomePayload.equals(offerGenres)) {
			outputMessageService.offerGenres(user);
		} else if (incomePayload.equals(cancel)) {
			userService.removeGenresAndYears(user);
			outputMessageService.offerGenres(user);
		} else if (incomePayload.matches(commentPayload + "\\d+")) {
			sendCommentQuickReply(user, incomePayload);
		} else if (incomePayload.matches(addCommentPayload + "\\d+")) {
			prepareUserForCommenting(user, incomePayload);
		} else if (incomePayload.matches(viewFilmCommentsPayload + "\\d+")) {
			showFilmComments(user, incomePayload);
		} else if (incomePayload.matches(viewCommentPayload + "\\d+")) {
			showCommentText(user, incomePayload);
		}else if(incomePayload.matches(rateFilmPayload + "\\d+")) {
			showRateCases(user, incomePayload);
		} else if (incomePayload.equals(offerYears)) {
			outputMessageService.offerYears(user);
		} else if (ifYears(incomePayload)) {
			userService.setUserYears(user, incomePayload);
			outputMessageService.offerFilms(user);
		} else if (genreService.ifGenre(incomePayload)) {
			userService.addGenreToUser(user, incomePayload);
			outputMessageService.offerGenres(user);
		}
		}
	}

	private void prepareUserForCommenting(User user, String incomePayload) {
		incomePayload = incomePayload.split("&")[1];
		if (filmService.getAllFilmId().contains(Integer.parseInt(incomePayload))) {
			userService.setCommentingFilmId(user, incomePayload);
			userService.setUserStatus(user, UserStatus.WRITING_COMMENT);
			outputMessageService.sendTextMessage(user, commentPls);
		}
	}
	
	private void sendCommentQuickReply(User user, String incomePayload) {
		incomePayload = incomePayload.split("&")[1];
		outputMessageService.sendCommentQuickReply(user, incomePayload);
	}

	private void showFilmComments(User user, String incomePayload) {
		incomePayload = incomePayload.split("&")[1];
		outputMessageService.showComments(user, incomePayload);
	}

	private void showCommentText(User user, String incomePayload) {
		incomePayload = incomePayload.split("&")[1];
		outputMessageService.showOneComment(user, incomePayload);
	}

	private void showRateCases(User user, String incomePayload) {
		incomePayload = incomePayload.split("&")[1];
		userService.setCommentingFilmId(user, incomePayload);
		userService.setUserStatus(user, UserStatus.RAITING_FILM);
		outputMessageService.offerRate(user);
	}
	
	private boolean ifYears(String testString) {
		Pattern p = Pattern.compile("^\\d\\d\\d\\d\\D\\d\\d\\d\\d$");
		Matcher m = p.matcher(testString);
		return m.matches();
	}

}

package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.Comment;
import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.model.other.AttachmentType;
import com.firstBot.model.other.ButtonType;
import com.firstBot.model.other.FilmComparator;
import com.firstBot.model.other.QuickReplyType;
import com.firstBot.model.other.TemplateType;
import com.firstBot.model.outputMessaging.Attachment;
import com.firstBot.model.outputMessaging.Button;
import com.firstBot.model.outputMessaging.ButtonPostback;
import com.firstBot.model.outputMessaging.ButtonUrl;
import com.firstBot.model.outputMessaging.Element;
import com.firstBot.model.outputMessaging.MessageOut;
import com.firstBot.model.outputMessaging.MessagingOut;
import com.firstBot.model.outputMessaging.Payload;
import com.firstBot.model.outputMessaging.QuickReply;
import com.firstBot.model.outputMessaging.Recipient;
import com.firstBot.service.CommentService;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.OutputMessageService;
import com.vdurmont.emoji.EmojiParser;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

@Service
public class OutputMessageServiceImpl implements OutputMessageService {

	@Value("${access}")
	String access;

	@Value("${url.bot}")
	String urlbot;

	@Value("${offer_genres}")
	String offerGenres;

	@Value("${qr.back.title}")
	String backTitle;

	@Value("${button.trailer.title}")
	String trailer;

	@Value("${yearCases}")
	String yearCases;

	@Value("${yearsInterval}")
	String yearsInterval;

	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${remove_years_and_genres}")
	String cancel;

	@Value("${offer_years}")
	String offerYears;

	@Value("${qr.done.title}")
	String donePayload;

	@Value("${dialogflow.client_access_token}")
	String clientAccessToken;

	@Value("${button.comment.add.title}")
	String addCommentTitle;

	@Value("${button.comment.add.payload}")
	String addCommentPayload;

	@Value("${button.view.film.comments.payload}")
	String viewFilmCommentsPayload;

	@Value("${button.view.film.comments.title}")
	String viewFilmCommentsTitle;

	@Value("${button.view.comment.payload}")
	String viewCommentPayload;

	@Value("${button.view.comment.title}")
	String viewCommentTitle;

	@Value("${no_films}")
	String noFilms;

	@Value("${no_comments}")
	String noComments;

	@Value("${rate_pls}")
	String ratePls;

	@Value("${rateSize}")
	String rateSize;

	@Value("${fullRateBar}")
	String fullRateBar;

	@Value("${emptyRateBar}")
	String emptyRatebar;

	@Value("${button.rate.title}")
	String rateFilmTitle;

	@Value("${button.rate.payload}")
	String rateFilmPayload;

	@Value("${button.comment.title}")
	String commentTitle;

	@Value("${button.comment.payload}")
	String commentPayload;

	@Value("${choose_genre_pls}")
	String chooseGenre;
	
	@Autowired
	private FilmService filmService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private CommentService commentService;
	
	 private static final org.apache.log4j.Logger logger = LogManager.getLogger(OutputMessageServiceImpl.class);
	
	public AIResponse sendToDialogflow(String text) {
		AIConfiguration config = new AIConfiguration(clientAccessToken);
		AIDataService dataService = new AIDataService(config);
		AIRequest request = new AIRequest(text);
		try {
			AIResponse response = dataService.request(request);
			return response;
		} catch (AIServiceException e) {
			logger.error(e);
		}
		return null;
	}

	public void sendGreetings(User user) {
		sendTextMessage(user, "Hi, " + user.getFirstName()
				+ "! Nice to meet you.\r\nMy name is FilmoZavr. I am bot that helps people to find interesting and cool films. What film genre do you like?");
	}

	public void sendTextMessage(User user, String text) {
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()), new MessageOut(text));
		rt.postForObject(urlbot + access, template, String.class);
	}

	public void offerFilms(User user) {
		List<Film> offeredFilms = filmService.getOfferedFilms(user.getMessengerUserId());
		Collections.sort(offeredFilms, new FilmComparator());
		List<Element> elementList = new ArrayList<Element>();
		for (int i = 0; i < offeredFilms.size(); i++) {
			Film film = offeredFilms.get(i);
			List<Button> buttons = getFilmButtons(user, film);
			elementList.add(new Element(film.getName(), film.getFilmUrl(), film.getDescription(), buttons));
		}
		sendGenericTemplateWithFilms(user, elementList);
	}

	private void sendGenericTemplateWithFilms(User user, List<Element> elementList) {
		RestTemplate rt = new RestTemplate();
		if (elementList.size() > 0) {
			MessagingOut gt = new MessagingOut(new Recipient(user.getMessengerUserId()), new MessageOut(
					new Attachment(AttachmentType.template, new Payload(TemplateType.generic, elementList))));
			rt.postForObject(urlbot + access, gt, String.class);
		} else {
			MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()), new MessageOut(noFilms));
			rt.postForObject(urlbot + access, template, String.class);
			offerGenres(user);
		}
	}

	private List<Button> getFilmButtons(User user, Film film) {
		List<Button> buttons = new ArrayList<Button>();
		ButtonUrl buttonUrl = new ButtonUrl(ButtonType.web_url, film.getTrailerUrl(), trailer);
		ButtonPostback comments = makeCommentsButton(film);
		ButtonPostback rateFilm = makeRateFilmButton(film, user);
		buttons.add(buttonUrl);
		buttons.add(comments);
		buttons.add(rateFilm);
		return buttons;
	}

	private ButtonPostback makeCommentsButton(Film film) {
		ButtonPostback button = new ButtonPostback(commentTitle, commentPayload + String.valueOf(film.getId()));
		return button;
	}

	private ButtonPostback makeRateFilmButton(Film film, User user) {
		ButtonPostback button = new ButtonPostback(rateFilmTitle, rateFilmPayload + String.valueOf(film.getId()));
		return button;
	}

	@Transactional
	public void offerGenres(User user) {
		List<Genre> offeredGenres = genreService.findByNameNotIn(user.getMessengerUserId()); // get genres to offer
		List<QuickReply> listQR = new ArrayList<QuickReply>();
		for (int i = 0; i < offeredGenres.size(); i++) {
			Genre tempGenre = offeredGenres.get(i);
			listQR.add(new QuickReply(QuickReplyType.text, tempGenre.getName(), tempGenre.getName()));
		}
		if (user.getGenres().size() > 0) {
			listQR.add(0, new QuickReply(QuickReplyType.text, donePayload, offerYears));
			listQR.add(new QuickReply(QuickReplyType.text, cancelTitle, cancel));
		}
		sendQuickReplys(user, chooseGenre, listQR);

	}

	private void sendQuickReplys(User user, String text, List<QuickReply> listQR) {
		MessageOut mes = new MessageOut(text, listQR);
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()), mes);
		rt.postForObject(urlbot + access, template, String.class);
	}

	public void offerYears(User user) {
		List<QuickReply> list = new ArrayList<QuickReply>();
		int tempYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int k = Integer.parseInt(yearCases); k >= 0; k--) {
			String years = "-" + (tempYear - Integer.parseInt(yearsInterval) * k);
			years = (tempYear - Integer.parseInt(yearsInterval) * (k + 1)) + years;
			list.add(new QuickReply(QuickReplyType.text, years, years, ""));
		}
		
		list.add(new QuickReply(QuickReplyType.text, backTitle, offerGenres, ""));
		MessageOut mes = new MessageOut("Choose years please.", list);
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()), mes);
		rt.postForObject(urlbot + access, template, String.class);
	}

	@Transactional
	@Override
	public void showComments(User user, String filmId) {
		Film film = filmService.findOne(Integer.parseInt(filmId));
		List<Comment> filmComments = film.getComments();
		List<Element> elementList = new ArrayList<Element>();
		for (int i = 0; i < filmComments.size(); i++) {
			Comment comment = filmComments.get(i);
			User userCommentator = comment.getUser();
			List<Button> buttons = new ArrayList<Button>();
			buttons.add(new ButtonPostback(viewCommentTitle, viewCommentPayload + comment.getId()));
			elementList.add(new Element(userCommentator.getFirstName() + " " + userCommentator.getLastName(),
					userCommentator.getProfile_pic(), EmojiParser.parseToUnicode(comment.getText()), buttons));
		}
		RestTemplate rt = new RestTemplate();
		if (elementList.size() > 0) {
			MessagingOut gt = new MessagingOut(new Recipient(user.getMessengerUserId()), new MessageOut(
					new Attachment(AttachmentType.template, new Payload(TemplateType.generic, elementList))));
			rt.postForObject(urlbot + access, gt, String.class);
		} else {
			sendTextMessage(user, noComments);
		}
	}

	@Transactional
	@Override
	public void showOneComment(User user, String commentId) {
		sendTextMessage(user,
				EmojiParser.parseToUnicode(commentService.findOne(Integer.parseInt(commentId)).getText()));
	}

	public void offerRate(User user) {
		List<QuickReply> listQR = new ArrayList<QuickReply>();
		fillQRWithRateCases(listQR);
		sendQuickReplys(user, ratePls, listQR);
	}

	private void fillQRWithRateCases(List<QuickReply> listQR) {
		for (int i = 1; i <= Integer.parseInt(rateSize); i++) {
			// String title = getRateTitle()
			String title = "";
			for (int j = 0; j < Integer.parseInt(rateSize); j++) {
				if (j < i)
					title += fullRateBar;
				else
					title += emptyRatebar;
			}
			listQR.add(new QuickReply(QuickReplyType.text, title, "" + i));
		}
	}

	@Override
	public void sendCommentQuickReply(User user, String filmId) {
		List<QuickReply> listQR = new ArrayList<QuickReply>();
		listQR.add(new QuickReply(QuickReplyType.text, viewFilmCommentsTitle, viewFilmCommentsPayload + filmId));
		listQR.add(new QuickReply(QuickReplyType.text, addCommentTitle, addCommentPayload + filmId));
		sendQuickReplys(user, "Would u like to see comments or leave the own one?", listQR);
	}

}
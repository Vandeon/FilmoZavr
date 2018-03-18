package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.model.other.AttachmentType;
import com.firstBot.model.other.ButtonType;
import com.firstBot.model.other.QuickReplyType;
import com.firstBot.model.other.TemplateType;
import com.firstBot.model.outputMessaging.Attachment;
import com.firstBot.model.outputMessaging.Button;
import com.firstBot.model.outputMessaging.ButtonUrl;
import com.firstBot.model.outputMessaging.Element;
import com.firstBot.model.outputMessaging.MessageOut;
import com.firstBot.model.outputMessaging.MessagingOut;
import com.firstBot.model.outputMessaging.Payload;
import com.firstBot.model.outputMessaging.QuickReply;
import com.firstBot.model.outputMessaging.Recipient;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.OutputMessageService;

@Service
public class OutputMessageServiceImpl implements OutputMessageService {

	@Value("${access}")
	String access;

	@Value("${url.bot}")
	String urlbot;

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

	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${qr.cancel.payload}")
	String cancelPayload;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${qr.done.payload}")
	String donePayload;

	@Autowired
	private FilmService filmService;

	@Autowired
	private GenreService genreService;

	public void sendTextMessage(User user, String text) {
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()),	new MessageOut(text));
		rt.postForObject(urlbot + access, template, String.class);
	}
	
	public void sendGreetings(User user) {
		String greetings = "Hi, " + user.getFirstName()
				+ "! Nice to meet you.\r\nMy name is FilmoZavr. I am bot that helps people to find interesting and cool films. What film genre do you like?";
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()),
				new MessageOut(greetings));
		rt.postForObject(urlbot + access, template, String.class);
	}

	public void offerFilms(User user) {
		List<Film> offeredFilms = filmService.getOfferedFilms(user.getMessengerUserId());
		List<Element> elementList = new ArrayList<Element>();
		// generateButtons
		for (int i = 0; i < offeredFilms.size(); i++) {
			Film film = offeredFilms.get(i);
			List<Button> buttons = new ArrayList<Button>();
			ButtonUrl buttonUrl = new ButtonUrl(ButtonType.web_url, film.getTrailerUrl(), trailer);
			buttons.add(buttonUrl);
			elementList.add(new Element(film.getName(), film.getFilmUrl(), film.getDescription(), buttons));
		}
		RestTemplate rt = new RestTemplate();
		if (elementList.size() > 0) {
			MessagingOut gt = new MessagingOut(new Recipient(user.getMessengerUserId()), new MessageOut(
					new Attachment(AttachmentType.template, new Payload(TemplateType.generic, elementList))));
			rt.postForObject(urlbot + access, gt, String.class);
		} else {
			MessagingOut template = new MessagingOut("", new Recipient(user.getMessengerUserId()),
					new MessageOut("Oooops... There are no films due to your parameters."));
			rt.postForObject(urlbot + access, template, String.class);
			offerGenres(user);
		}
	}

	public void offerGenres(User user) {
		List<Genre> offeredGenres = genreService.findByNameNotIn(user.getMessengerUserId()); // Витягує з бази всі
																								// жанри, які ще не
																								// обрав юзер
		List<QuickReply> list = new ArrayList<QuickReply>();
		for (int i = 0; i < offeredGenres.size(); i++) {
			Genre tempGenre = offeredGenres.get(i);
			list.add(new QuickReply(QuickReplyType.text, tempGenre.getName(), String.valueOf(tempGenre.getId())));
		}
		if (user.getGenres().size() > 0) {
			list.add(0, new QuickReply(QuickReplyType.text, doneTitle, donePayload));
			list.add(new QuickReply(QuickReplyType.text, cancelTitle, cancelPayload));
		}
		MessageOut mes = new MessageOut("Choose the genre please.", list);
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
		list.add(new QuickReply(QuickReplyType.text, backTitle, backPayload, ""));
		MessageOut mes = new MessageOut("Choose years please.", list);
		RestTemplate rt = new RestTemplate();
		MessagingOut template = new MessagingOut(new Recipient(user.getMessengerUserId()), mes);
		rt.postForObject(urlbot + access, template, String.class);
	}
}

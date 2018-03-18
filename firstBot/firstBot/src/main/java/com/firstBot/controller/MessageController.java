package com.firstBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstBot.model.incomeMessaging.IncommingMessage;
import com.firstBot.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
//	@Autowired
//	private FilmService filmService;
//	@Autowired
//	private GenreService genreService;
	
	@PostMapping
	public void getMessage(@RequestBody IncommingMessage message) {
//		ContentMaker cm = new ContentMaker();
//		cm.makeContent(filmService, genreService);
		messageService.getMessage(message);
	}

}


//ContentMaker cm = new ContentMaker();
//cm.makeContent(filmService, genreService);
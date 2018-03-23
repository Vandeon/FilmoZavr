package com.firstBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstBot.model.incomeMessaging.IncommingMessage;
import com.firstBot.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping
	public ResponseEntity<HttpStatus> getMessage(@RequestBody IncommingMessage message) {
		messageService.getMessage(message);
		return ResponseEntity.ok().build();
	}

}
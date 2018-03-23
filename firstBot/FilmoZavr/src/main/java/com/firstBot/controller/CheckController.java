package com.firstBot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

	@Value("${secret}")
	private String secret;

	@GetMapping
	public String get(
			@RequestParam(value="hub.verify_token") String verify,
			@RequestParam(value="hub.challenge") String challenge,
			@RequestParam(value="hub.mode") String mode 
			) {
			if(verify.equals(secret)) {
				return challenge;
			}
			return "";
	}
	
}

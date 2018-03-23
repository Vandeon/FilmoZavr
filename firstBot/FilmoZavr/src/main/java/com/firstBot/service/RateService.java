package com.firstBot.service;

import java.util.List;

import com.firstBot.entity.Film;
import com.firstBot.entity.Rate;
import com.firstBot.entity.User;

public interface RateService {

	void save(Rate rate);
	
	List<Rate> findAll();
	
	Rate findOne(int id);
	
	Rate findByUserAndFilm(String userMessengerId, String filmId);
	
	void remove(int id);
	
	void addOrUpdateRate(int mark, User user, Film film);
	
}

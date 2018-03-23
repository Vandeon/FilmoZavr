package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Film;
import com.firstBot.entity.Rate;
import com.firstBot.entity.User;
import com.firstBot.repository.RateRepository;
import com.firstBot.service.FilmService;
import com.firstBot.service.RateService;

@Service
public class RateServiceImpl implements RateService {

	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private FilmService filmService;

	@Override
	public void save(Rate rate) {
		rateRepository.save(rate);
	}

	@Override
	public List<Rate> findAll() {
		return rateRepository.findAll();
	}

	@Override
	public Rate findOne(int id) {
		return rateRepository.getOne(id);
	}

	@Override
	public Rate findByUserAndFilm(String userMessengerId, String filmId) {
		return rateRepository.findByUserAndFilm(userMessengerId, filmId);
	}

	@Override
	public void remove(int id) {
		rateRepository.deleteById(id);
	}

	@Override
	public void addOrUpdateRate(int mark, User user, Film film) {
		Rate rate = findByUserAndFilm(String.valueOf(user.getId()), String.valueOf(film.getId()));
		if (rate != null) {
			remove(rate.getId());
		}
		addRate(mark, user, film);
		filmService.updateAvgRaiting(film.getId());
	}

	private void addRate(int mark, User user, Film film) {
		Rate rate = new Rate(mark, user, film);
		rateRepository.save(rate);
	}
}

package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.repository.FilmRepository;
import com.firstBot.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService{

	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	public void save(Film film) {
		filmRepository.save(film);
	}

	@Override
	public List<Film> findAll() {
		return filmRepository.findAll();
	}

	@Override
	public Film findOne(int id) {
		return filmRepository.getOne(id);
	}

	@Override
	public boolean remove(int id) {
		filmRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Film> findByGenres(List<Genre> genres) {
		return filmRepository.findByGenre(genres);
	}

	@Override
	public List<Film> findByYear(int year) {
		return filmRepository.findByYear(year);
	}
	
	@Override
	public List<Film> getOfferedFilms(String messengerUserId) {
		return filmRepository.getOfferedFilms(messengerUserId);
	}

}

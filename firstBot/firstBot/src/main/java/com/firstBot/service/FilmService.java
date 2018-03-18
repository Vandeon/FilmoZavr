package com.firstBot.service;

import java.util.List;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;

public interface FilmService {

	void save(Film film);
	
	List<Film> findAll();
	
	Film findOne(int id);
	
	boolean remove(int id);

	List<Film> findByGenres(List<Genre> genres);
	
	List<Film> findByYear(int year);
	
	List<Film> getOfferedFilms(String messengerUserId);
	
}

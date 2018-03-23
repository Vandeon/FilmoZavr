package com.firstBot.service;

import java.util.List;

import com.firstBot.entity.Genre;

public interface GenreService {

	void save(Genre genre);

	List<Genre> findAll();

	Genre findOne(int id);

	boolean remove(int id);
	
	Genre findByName(String name);
	
	List<Genre> findByNameNotIn(String messengerUserId);
	
	public boolean ifGenre(String possibleGenre);

}

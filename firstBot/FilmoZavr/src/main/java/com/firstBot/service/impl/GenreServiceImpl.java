package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Genre;
import com.firstBot.repository.GenreRepository;
import com.firstBot.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService{

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);	
	}

	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	public Genre findOne(int id) {
		return genreRepository.getOne(id);
	}

	@Override
	public boolean remove(int id) {
		genreRepository.deleteById(id);
		return true;
	}

	@Override
	public Genre findByName(String name) {
		return genreRepository.findByName(name);
	}

	@Override
	public List<Genre> findByNameNotIn(String messengerUserId) {
		return genreRepository.findByNameNotIn(messengerUserId);
	}

	public boolean ifGenre(String possibleGenre) {
		if(genreRepository.getAllNames().contains(possibleGenre)) {
			return true;
		}
		return false;
	}
	
}

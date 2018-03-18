package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.repository.UserRepository;
import com.firstBot.service.GenreService;
import com.firstBot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Value("${url.facebook}")
	String url1;

	@Value("${url.getusername}")
	String url2;

	@Value("${access}")
	String access;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private GenreService genreService;
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public boolean remove(int id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public User findByMessengerUserId(String messengerUserId) {
		return userRepository.findByMessengerUserId(messengerUserId);
	}

	@Override
	public String getUserUrl(User user) {
		String userURL = url1 + user.getMessengerUserId() + url2 + access;
		return userURL;
	}

	@Override
	public void removeGenresAndYears(User user) {
		user.setGenres(new ArrayList<Genre>());
		user.setFromYear(0);
		user.setToYear(0);
		save(user);
	}

	@Override
	public void addGenreToUser(User user, String incomePayload) {
		Genre possibleGenre = genreService.findOne(Integer.parseInt(incomePayload));
		List<Genre> userGenres = user.getGenres();
		userGenres.add(possibleGenre);
		user.setGenres(userGenres);
		save(user);
	}

	@Override
	public void setUserYears(User user, String incomePayload) {
		String years[] = incomePayload.split("-");
		user.setFromYear(Integer.parseInt(years[0]));
		user.setToYear(Integer.parseInt(years[1]));
		save(user);
	}


}

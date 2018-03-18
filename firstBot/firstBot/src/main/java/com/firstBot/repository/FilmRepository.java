package com.firstBot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;

public interface FilmRepository extends JpaRepository<Film, Integer> {

	List<Film> findByGenre(List<Genre> genres);

	List<Film> findByYear(int year);

	@Query(value = "SELECT * from film where film.year >= (select from_year from userr where userr.messenger_user_id =:userId)\r\n" 
			+ "and film.year <= (select to_year from userr where userr.messenger_user_id =:userId) and film.id in (\r\n"
			+ "select film.id from film join film_genre on film.id=film_genre.id_film join genre on film_genre.id_genre=genre.id\r\n" 
			+ "join user_genre on genre.id=user_genre.id_genre join userr on user_genre.id_user=userr.id where userr.messenger_user_id =:userId)",
			nativeQuery = true)
	List<Film> getOfferedFilms(@Param("userId") String messengerUserId);

}

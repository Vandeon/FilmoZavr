package com.firstBot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.firstBot.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	Genre findByName(String name);
	
	@Query(value="select * from genre where genre.name not in (select genre.name from userr join user_genre on userr.id = user_genre.id_user join genre on user_genre.id_genre = genre.id where userr.messenger_user_id =:userId)", nativeQuery=true)
	List <Genre> findByNameNotIn(@Param("userId") String messengerUserId);
	
}

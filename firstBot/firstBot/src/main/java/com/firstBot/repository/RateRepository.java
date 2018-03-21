package com.firstBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.firstBot.entity.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer>{

	@Query(value="SELECT * FROM rate where user_id =:userId and  film_id =:filmId", nativeQuery=true)
	Rate findByUserAndFilm(@Param("userId")String userId, @Param("filmId")String filmId);
	
}

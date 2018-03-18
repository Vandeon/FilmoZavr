package com.firstBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstBot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByMessengerUserId(String messengerUserId);
	
}

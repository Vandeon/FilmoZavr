package com.firstBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstBot.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}

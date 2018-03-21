package com.firstBot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Comment;
import com.firstBot.repository.CommentRepository;
import com.firstBot.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void addComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public Comment findOne(int id) {
		return commentRepository.getOne(id);
	}

}

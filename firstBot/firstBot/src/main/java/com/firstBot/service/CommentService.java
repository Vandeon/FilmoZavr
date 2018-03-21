package com.firstBot.service;

import com.firstBot.entity.Comment;

public interface CommentService {
	
	void addComment(Comment comment);
	
	Comment findOne(int id);
	
}

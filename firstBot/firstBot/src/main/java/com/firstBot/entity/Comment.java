package com.firstBot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=10000)
	private String text;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Film film;
	
	public Comment() {}

	public Comment(int id, String text, User user, Film film) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.film = film;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", user=" + user + ", film=" + film + "]";
	}	

}

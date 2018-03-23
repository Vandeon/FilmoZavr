package com.firstBot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int mark;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Film film;

	public Rate() {}
	
	public Rate(int mark, User user, Film film) {
		super();
		this.mark = mark;
		this.user = user;
		this.film = film;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
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
		return "Rate [id=" + id + ", mark=" + mark + ", user=" + user.getFirstName() + " " + user.getLastName() + ", film=" + film.getName() + "]";
	}
	
}

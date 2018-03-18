package com.firstBot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToMany
	@JoinTable(name="film_genre", joinColumns = @JoinColumn(name="id_genre"),
	inverseJoinColumns = @JoinColumn(name="id_film"))
	private List<Film> films = new ArrayList<Film>();

	@ManyToMany
	@JoinTable(name="user_genre", joinColumns = @JoinColumn(name="id_genre"),
	inverseJoinColumns = @JoinColumn(name="id_user"))
	private List<User> users = new ArrayList<User>();
	
	public Genre() {}

	public Genre(int id, String name, List<Film> films, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.films = films;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name+ "]";
	}

	

}

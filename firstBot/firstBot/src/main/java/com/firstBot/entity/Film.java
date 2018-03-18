package com.firstBot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String filmUrl;

	private String trailerUrl;
	
	@Column(length=10000)
	private String description;
	
	@ManyToMany
	@JoinTable(name="film_genre", joinColumns = @JoinColumn(name="id_film"),
	inverseJoinColumns = @JoinColumn(name="id_genre"))
	private List<Genre> genre = new ArrayList<Genre>();
	
	private int year;

	@OneToMany(mappedBy="film")
	private List<Comment> comments;
	
	public Film() {}
	
	public Film(int id, String name, String filmUrl, String trailerUrl, String description, List<Genre> genre, int year,
			List<Comment> comments) {
		super();
		this.id = id;
		this.name = name;
		this.filmUrl = filmUrl;
		this.trailerUrl = trailerUrl;
		this.description = description;
		this.genre = genre;
		this.year = year;
		this.comments = comments;
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

	public String getFilmUrl() {
		return filmUrl;
	}

	public void setFilmUrl(String filmUrl) {
		this.filmUrl = filmUrl;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", genre=" + genre + ", year=" + year
				+ "]";
	}

	
	
}

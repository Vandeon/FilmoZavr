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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="userr")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String messengerUserId;
	
	private String firstName;
	
	private String lastName;
	
	@ManyToMany
	@JoinTable(name="user_genre", joinColumns = @JoinColumn(name="id_user"),
	inverseJoinColumns = @JoinColumn(name="id_genre"))
	private List<Genre> genres = new ArrayList<Genre>();
	
	private int fromYear;
	
	private int toYear;
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	public User() {}

	public User(int id, String messengerUserId, String firstName, String lastName, List<Genre> genres, int fromYear,
			int toYear, List<Comment> comments) {
		super();
		this.id = id;
		this.messengerUserId = messengerUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.genres = genres;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessengerUserId() {
		return messengerUserId;
	}

	public void setMessengerUserId(String messengerUserId) {
		this.messengerUserId = messengerUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", messengerUserId=" + messengerUserId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", fromYear=" + fromYear + ", toYear=" + toYear + "]";
	}

	
}

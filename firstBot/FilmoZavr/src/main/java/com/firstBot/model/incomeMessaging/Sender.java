package com.firstBot.model.incomeMessaging;

public class Sender {

	private String id;

	public Sender() {}
	
	public Sender(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Sender [id=" + id + "]";
	}
	
}

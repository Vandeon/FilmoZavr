package com.firstBot.model.incomeMessaging;

public class Postback {

	String payload;
	
	String title;

	public Postback() {}
	
	public Postback(String payload, String title) {
		super();
		this.payload = payload;
		this.title = title;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Postback [payload=" + payload + ", title=" + title + "]";
	}
		
}

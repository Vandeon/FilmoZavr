package com.firstBot.model.outputMessaging;

import com.firstBot.model.other.ButtonType;

public class ButtonPostback extends Button{

	private ButtonType type;
	
	private String title;
	
	private String payload;
	
	public ButtonPostback() {}
	
	public ButtonPostback(ButtonType type, String title, String payload) {
		super();
		this.type = type;
		this.title = title;
		this.payload = payload;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ButtonPostback [type=" + type + ", title=" + title + ", payload=" + payload + "]";
	}
	
	
}

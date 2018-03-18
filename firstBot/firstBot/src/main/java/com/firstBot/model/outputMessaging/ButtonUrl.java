package com.firstBot.model.outputMessaging;

import com.firstBot.model.other.ButtonType;

public class ButtonUrl extends Button{

	private ButtonType type;
	
	private String url;
	
	private String title;
	
	public ButtonUrl() {}
	
	public ButtonUrl(ButtonType type, String url, String title) {
		super();
		this.type = type;
		this.url = url;
		this.title = title;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ButtonUrl [type=" + type + ", url=" + url + ", title=" + title + "]";
	}
		
}

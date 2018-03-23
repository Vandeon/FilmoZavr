package com.firstBot.model.outputMessaging;

import java.util.List;

public class Element {

	private String title;
	
	private String image_url;
	
	private String subtitle;
	
	private DefaultAction default_action;
	
	private List<Button> buttons;
	
	public Element() {}

	public Element(String title, String image_url, String subtitle) {
		super();
		this.title = title;
		this.image_url = image_url;
		this.subtitle = subtitle;
	}

	public Element(String title, String image_url, String subtitle, DefaultAction default_action) {
		super();
		this.title = title;
		this.image_url = image_url;
		this.subtitle = subtitle;
		this.default_action = default_action;
	}

	public Element(String title, String image_url, String subtitle,	List<Button> buttons) {
		super();
		this.title = title;
		this.image_url = image_url;
		this.subtitle = subtitle;
		this.buttons = buttons;
	}

	public Element(String title, String image_url, String subtitle, DefaultAction default_action,
			List<Button> buttons) {
		super();
		this.title = title;
		this.image_url = image_url;
		this.subtitle = subtitle;
		this.default_action = default_action;
		this.buttons = buttons;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public DefaultAction getDefault_action() {
		return default_action;
	}

	public void setDefault_action(DefaultAction default_action) {
		this.default_action = default_action;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	@Override
	public String toString() {
		return "Element [title=" + title + ", image_url=" + image_url + ", subtitle=" + subtitle + "]";
	}
	
	
	
}

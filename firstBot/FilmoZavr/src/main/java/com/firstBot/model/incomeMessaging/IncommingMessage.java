package com.firstBot.model.incomeMessaging;

import java.util.List;

public class IncommingMessage {

	private String object;

	private List<Entry> entry;

	public IncommingMessage() {}
	
	public IncommingMessage(String object, List<Entry> entry) {
		super();
		this.object = object;
		this.entry = entry;
	}
	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "IncommingMessage [object=" + object + ", entry=" + entry + "]";
	}
	
	
	
}

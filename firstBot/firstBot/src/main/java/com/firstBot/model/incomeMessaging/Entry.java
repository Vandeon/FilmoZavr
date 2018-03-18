package com.firstBot.model.incomeMessaging;

import java.util.List;

public class Entry {

	private String id;
	
	private long time;
	
	private List<MessagingIn> messaging;

	public Entry() {}
	
	public Entry(String id, int time, List<MessagingIn> messaging) {
		super();
		this.id = id;
		this.time = time;
		this.messaging = messaging;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<MessagingIn> getMessaging() {
		return messaging;
	}

	public void setMessaging(List<MessagingIn> messaging) {
		this.messaging = messaging;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", time=" + time + ", messaging=" + messaging + "]";
	}
	
	
	
}

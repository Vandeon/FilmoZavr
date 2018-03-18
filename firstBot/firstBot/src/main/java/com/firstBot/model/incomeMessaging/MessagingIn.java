package com.firstBot.model.incomeMessaging;

import com.firstBot.model.outputMessaging.Recipient;

public class MessagingIn {

	private Sender sender;
	
	private Recipient recipient;
	
	private long timestamp;
	
	private MessageIn message;

	public MessagingIn() {}
	
	public MessagingIn(Sender sender, Recipient recipient, int timestamp, MessageIn message) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.timestamp = timestamp;
		this.message = message;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public MessageIn getMessage() {
		return message;
	}

	public void setMessage(MessageIn message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Messaging [sender=" + sender + ", recipient=" + recipient + ", message=" + message + "]";
	}
	
	
	
}

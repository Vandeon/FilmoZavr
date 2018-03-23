package com.firstBot.model.outputMessaging;

public class MessagingOut {

	private String messaging_type;
	
	private Recipient recipient;
	
	private MessageOut message;

	public MessagingOut(Recipient recipient, MessageOut message) {
		super();
		this.recipient = recipient;
		this.message = message;
	}

	public MessagingOut(String messaging_type, Recipient recipient, MessageOut message) {
		super();
		this.messaging_type = messaging_type;
		this.recipient = recipient;
		this.message = message;
	}

	public String getMessaging_type() {
		return messaging_type;
	}

	public void setMessaging_type(String messaging_type) {
		this.messaging_type = messaging_type;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public MessageOut getMessage() {
		return message;
	}

	public void setMessage(MessageOut message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessagingOut [messaging_type=" + messaging_type + ", recipient=" + recipient + ", message=" + message
				+ "]";
	}

}

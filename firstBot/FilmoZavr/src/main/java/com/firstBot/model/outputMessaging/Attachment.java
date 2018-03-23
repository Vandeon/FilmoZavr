package com.firstBot.model.outputMessaging;

import com.firstBot.model.other.AttachmentType;

public class Attachment {

	private AttachmentType type;
	
	private Payload payload;
	
	public Attachment() {}

	public Attachment(AttachmentType type, Payload payload) {
		super();
		this.type = type;
		this.payload = payload;
	}

	public AttachmentType getType() {
		return type;
	}

	public void setType(AttachmentType type) {
		this.type = type;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Attachment [type=" + type + ", payload=" + payload + "]";
	}

	
	
}

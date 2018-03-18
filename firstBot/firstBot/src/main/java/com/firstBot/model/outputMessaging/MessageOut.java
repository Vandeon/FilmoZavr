package com.firstBot.model.outputMessaging;

import java.util.List;

public class MessageOut {

	private String text;

	private List<QuickReply> quick_replies;

	private Attachment attachment;

	public MessageOut() {
	}

	public MessageOut(String text) {
		super();
		this.text = text;
	}

	public MessageOut(String text, List<QuickReply> quick_replies) {
		super();
		this.text = text;
		this.quick_replies = quick_replies;
	}

	public MessageOut(Attachment attachment) {
		super();
		this.attachment = attachment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<QuickReply> getQuick_replies() {
		return quick_replies;
	}

	public void setQuick_replies(List<QuickReply> quick_replies) {
		this.quick_replies = quick_replies;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "MessageOut [text=" + text + ", quick_replies=" + quick_replies + ", attachment=" + attachment + "]";
	}

}

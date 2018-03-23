package com.firstBot.model.incomeMessaging;

import com.firstBot.model.outputMessaging.QuickReply;

public class MessageIn {

	private QuickReply quick_reply;
	
	private String mid;
	
	private int seq;
	
	private String text;

	public MessageIn() {}
	
	public MessageIn(String mid, int seq, String text) {
		super();
		this.mid = mid;
		this.seq = seq;
		this.text = text;
	}
	
	public MessageIn(QuickReply quick_reply, String mid, int seq, String text) {
		super();
		this.quick_reply = quick_reply;
		this.mid = mid;
		this.seq = seq;
		this.text = text;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuickReply getQuick_reply() {
		return quick_reply;
	}

	public void setQuick_reply(QuickReply quick_reply) {
		this.quick_reply = quick_reply;
	}

	@Override
	public String toString() {
		return "Message [quick_reply=" + quick_reply + ", mid=" + mid + ", seq=" + seq + ", text=" + text + "]";
	}

	
	
}

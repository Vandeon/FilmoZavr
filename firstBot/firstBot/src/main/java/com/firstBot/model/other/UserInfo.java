package com.firstBot.model.other;

public class UserInfo {

	private String first_name;
	
	private String last_name;
	
	private String profile_pic;
	
	private String id;

	public UserInfo() {}

	public UserInfo(String first_name, String last_name, String profile_pic, String id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.profile_pic = profile_pic;
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	@Override
	public String toString() {
		return "UserInfo [first_name=" + first_name + ", last_name=" + last_name + ", id=" + id + "]";
	}
	
}

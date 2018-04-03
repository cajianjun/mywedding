package com.cjj.request;

public class UserInfoRequest extends TokenRequest{
	private String userinfo;

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	public UserInfoRequest() {
		super();
	}

	@Override
	public String toString() {
		return "UserInfoRequest [userinfo=" + userinfo + ", token=" + token + "]";
	}
	
	
}

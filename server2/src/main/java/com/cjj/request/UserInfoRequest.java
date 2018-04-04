package com.cjj.request;

import com.cjj.dto.WXUserDTO;

public class UserInfoRequest extends TokenRequest{
	private WXUserDTO data;

	public WXUserDTO getData() {
		return data;
	}

	public void setData(WXUserDTO data) {
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "UserInfoRequest [data=" + data + ", token=" + token + "]";
	}

	public UserInfoRequest() {
		super();
	}
	
}

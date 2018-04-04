package com.cjj.wedding.service;

import com.cjj.dto.WXUserDTO;

public interface UserService {
	
	String getTokenFromWX(String code);
	
	String checkInvite(String token,WXUserDTO userinfo);
}

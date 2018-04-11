package com.cjj.wedding.service;

import com.cjj.dto.InviteInfoDTO;
import com.cjj.dto.WXUserDTO;

public interface UserService {
	
	String getTokenFromWX(String code);
	
	InviteInfoDTO checkInvite(String token,WXUserDTO userinfo);
	
	void addMsg(String token,String msg);
}

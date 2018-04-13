package com.cjj.wedding.service;

import java.util.List;

import com.cjj.dto.InviteInfoDTO;
import com.cjj.dto.MsgDTO;
import com.cjj.dto.WXUserDTO;
import com.cjj.request.PageRequest;

public interface UserService {
	
	String getTokenFromWX(String code);
	
	InviteInfoDTO checkInvite(String token,WXUserDTO userinfo);
	
	void addMsg(String token,String msg);

	List<MsgDTO> listMsg(PageRequest req);
}

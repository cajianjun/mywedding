package com.cjj.wedding.service;


public interface UserService {
	
	String getTokenFromWX(String code);
	
	String checkInvite(String token,String userinfo);
}

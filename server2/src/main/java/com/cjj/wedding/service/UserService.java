package com.cjj.wedding.service;

import com.cjj.entitys.UserEntity;

public interface UserService {
	UserEntity getUser(String userjson);
	
	String getTokenFromWX(String code);
}

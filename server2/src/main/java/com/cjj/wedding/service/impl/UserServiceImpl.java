package com.cjj.wedding.service.impl;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.cjj.entitys.UserEntity;
import com.cjj.wedding.service.UserService;

public class UserServiceImpl implements UserService{
	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();  
    
	
	private RestTemplate getRestTemplate() {
		requestFactory.setConnectTimeout(10000);// 设置超时  
	    requestFactory.setReadTimeout(10000); 
	    RestTemplate restTemplate = new RestTemplate(requestFactory);  
	    return restTemplate;
	}
	
	@Override
	public UserEntity getUser(String userjson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenFromWX() {
		// TODO Auto-generated method stub
		RestTemplate rest = getRestTemplate();
//		https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		return null;
	}

}

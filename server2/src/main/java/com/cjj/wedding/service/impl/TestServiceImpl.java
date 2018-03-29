package com.cjj.wedding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjj.entitys.UserEntity;
import com.cjj.mapper.UserMapper;
import com.cjj.util.JSONUtils;
import com.cjj.wedding.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	
	@Autowired
	UserMapper userMapper;
	@Override
	public String test() {
		// TODO Auto-generated method stub
		UserEntity ett = userMapper.getOneById(1l);
		return JSONUtils.Obj2Json(ett);
	}

}

package com.cjj.wedding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjj.response.GeneralResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ApiOperation(value="获取用户openid和sessionkey", notes="从微信服务端获取openid和sessionkey", produces = "application/json")  
	@RequestMapping(value = "token", method = RequestMethod.POST)
	public GeneralResponse token(String code) {
		logger.info("countByYear code=" + code);
		
		return esoperateService.countByYear(request);
	}
}

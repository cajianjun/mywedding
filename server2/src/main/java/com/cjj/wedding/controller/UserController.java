package com.cjj.wedding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjj.request.UserInfoRequest;
import com.cjj.response.GeneralResponse;
import com.cjj.util.StrUtils;
import com.cjj.wedding.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value="获取用户openid和sessionkey", notes="从微信服务端获取openid和sessionkey", produces = "application/json")  
	@RequestMapping(value = "token", method = RequestMethod.POST)
	public GeneralResponse token(String code) {
		logger.info("countByYear code=" + code);
		
		return GeneralResponse.SUCCESS();
	}
	
	@ApiOperation(value="判断用户信息是否在邀请列表中，根据昵称判断", notes="根据昵称判断用户是否在邀请列表中", produces = "application/json")  
	@RequestMapping(value = "invite", method = RequestMethod.POST)
	public GeneralResponse invite(UserInfoRequest req) {
		logger.info("invote,req = " + req);
		String realname = userService.checkInvite(req.getToken(),req.getUserinfo());
		if(StrUtils.isEmpty(realname)) {
			return GeneralResponse.FAILED();
		}else {
			return GeneralResponse.SUCCESS(realname);
		}
	}
	
}

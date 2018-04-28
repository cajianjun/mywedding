package com.cjj.wedding.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjj.dto.InviteInfoDTO;
import com.cjj.dto.MsgDTO;
import com.cjj.request.CodeRequest;
import com.cjj.request.DataRequest;
import com.cjj.request.MsgListRequest;
import com.cjj.request.PageRequest;
import com.cjj.request.UserInfoRequest;
import com.cjj.response.GeneralResponse;
import com.cjj.util.EmojiFilter;
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
	public GeneralResponse token(@RequestBody CodeRequest code) {
		logger.info("token code=" + code.getCode());
		String token = userService.getTokenFromWX( code.getCode());
		return GeneralResponse.SUCCESS(token);
	}
	
	@ApiOperation(value="判断用户信息是否在邀请列表中，根据昵称判断", notes="根据昵称判断用户是否在邀请列表中", produces = "application/json")  
	@RequestMapping(value = "invite", method = RequestMethod.POST)
	public GeneralResponse invite(@RequestBody UserInfoRequest req) {
		logger.info("invote,req = " + req);
		req.getData().setNickName(EmojiFilter.filterEmoji(req.getData().getNickName()));
		InviteInfoDTO dto = userService.checkInvite(req.getToken(),req.getData());
		return GeneralResponse.SUCCESS(dto);
	}
	
	@ApiOperation(value="获取用户openid和sessionkey", notes="从微信服务端获取openid和sessionkey", produces = "application/json")  
	@RequestMapping(value = "msg", method = RequestMethod.POST)
	public GeneralResponse msg(@RequestBody DataRequest req) {
		logger.info("msg req=" + req.toString());
		userService.addMsg(req.getToken(), EmojiFilter.filterEmoji(req.getData()));
		return GeneralResponse.SUCCESS();
	}
	
	@ApiOperation(value="获取用户openid和sessionkey", notes="从微信服务端获取openid和sessionkey", produces = "application/json")  
	@RequestMapping(value = "getMsg", method = RequestMethod.POST)
	public GeneralResponse getMsg(@RequestBody MsgListRequest req) {
		logger.info("getMsg req=" + req.toString());
		List<MsgDTO> dtos = userService.listMsg(req);
		return GeneralResponse.SUCCESS(dtos);
	}
	
}

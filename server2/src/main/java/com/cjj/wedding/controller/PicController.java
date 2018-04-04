package com.cjj.wedding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjj.request.DataRequest;
import com.cjj.response.GeneralResponse;
import com.cjj.wedding.service.PicService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("pic")
public class PicController {
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	PicService picService;
	
	@ApiOperation(value="获取图片组", notes="获取所有的图片组", produces = "application/json")  
	@RequestMapping(value = "group", method = RequestMethod.GET)
	public GeneralResponse group() {
		logger.info("group");
		return GeneralResponse.SUCCESS(picService.getGroup());
	}
	
	@ApiOperation(value="获取某组图片的所有图片", notes="获取某组图片的所有图片", produces = "application/json")  
	@RequestMapping(value = "pics", method = RequestMethod.POST)
	public GeneralResponse pics(@RequestBody DataRequest req) {
		logger.info("pics req=" + req.toString());
		return GeneralResponse.SUCCESS(picService.getPics(Integer.valueOf(req.getData())));
	}
}

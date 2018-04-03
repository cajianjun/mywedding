package com.cjj.wedding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.cjj.common.WXConsts;
import com.cjj.entitys.InvitedEntity;
import com.cjj.entitys.WxUserEntity;
import com.cjj.mapper.WXUserMapper;
import com.cjj.util.StrUtils;
import com.cjj.wedding.service.UserService;

public class UserServiceImpl implements UserService{
	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();  
    
	@Autowired
	WXUserMapper wXUserMapper;
	
	
	private RestTemplate getRestTemplate() {
		requestFactory.setConnectTimeout(10000);// 设置超时  
	    requestFactory.setReadTimeout(10000); 
	    RestTemplate restTemplate = new RestTemplate(requestFactory);  
	    return restTemplate;
	}
	

	@Override
	public String getTokenFromWX(String code) {
		// TODO Auto-generated method stub
		RestTemplate rest = getRestTemplate();
		String url = WXConsts.WX_TOKEN_URL_PREFIX + code ;
		String s = rest.postForObject(url,new Object(),String.class);
		//TODO token是获取来的，从s
		String token = "";
		WxUserEntity ett = new WxUserEntity();
		ett.setWxToken(token);
		wXUserMapper.insert(ett);
		//获取到token ，sessionkey
		return token;
	}

	@Override
	public String checkInvite(String token,String userinfo) {
		
		String inviteName = "";
		//从userinfo里面获取nickname
		String nickname = "";

		WxUserEntity wxUser = wXUserMapper.getOneByToken(token);
		String rn = wxUser.getRealName();
		if(StrUtils.isNotEmpty(rn)) {
			//如果已经能查到realname，直接返回，已经被邀请
			inviteName = rn;
		}else {
			//如果user表中没有标记真名
			List<InvitedEntity> all = wXUserMapper.getAllInvited();
			List<String> invitedNicks = new ArrayList<String>();
			all.forEach(x->{
				invitedNicks.add(x.getInvitedNick());
			});
			if(invitedNicks.contains("ALL")) {
				//全通过模式,都接受邀请，邀请的名字是nickname
				inviteName = nickname;
			}
			for(InvitedEntity x :all){
				if(x.getInvitedNick().equals(nickname)) {
					//在被邀请名单中，需要
					wXUserMapper.updateWxUserRealName(token,x.getRealName());
					inviteName = x.getRealName();
				}
			};
		}
		wXUserMapper.updateWxUser(token, nickname, userinfo);
		return inviteName;
	}

}

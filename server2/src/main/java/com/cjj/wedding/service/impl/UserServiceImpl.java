package com.cjj.wedding.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cjj.common.WXConsts;
import com.cjj.dto.InviteInfoDTO;
import com.cjj.dto.MsgDTO;
import com.cjj.dto.MsgEntityDTO;
import com.cjj.dto.WXUserDTO;
import com.cjj.entitys.InvitedEntity;
import com.cjj.entitys.UserMsgEntity;
import com.cjj.entitys.WxUserEntity;
import com.cjj.mapper.MsgMapper;
import com.cjj.mapper.WXUserMapper;
import com.cjj.request.MsgListRequest;
import com.cjj.request.PageRequest;
import com.cjj.util.JSONUtils;
import com.cjj.util.StrUtils;
import com.cjj.wedding.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();  
    
	@Autowired
	WXUserMapper wXUserMapper;

	@Autowired
	MsgMapper msgMapper;
	
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
		String s = rest.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		String token = "";
		try {
			Map<String,String> map = mapper.readValue(s,Map.class);
			//sessionKey用户每一次重新打开都会变
			String sessionKey = map.get("session_key");
			//openid每次重新打开都不变
			String openId = map.get("openid");
			token = openId;
			
			logger.info("openid=" + openId + ",sessionKey=" + sessionKey);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WxUserEntity wxUser = wXUserMapper.getOneByToken(token);
		if(wxUser == null) {
			WxUserEntity ett = new WxUserEntity();
			ett.setWxToken(token);
			wXUserMapper.insert(ett);
		}
		return token;
	}

	@Override
	public InviteInfoDTO checkInvite(String token,WXUserDTO userinfo) {
		InviteInfoDTO dto = new InviteInfoDTO();
		String inviteName = "";
		//从userinfo里面获取nickname
		String nickname = userinfo.getNickName();
		WxUserEntity wxUser = wXUserMapper.getOneByToken(token);
		String rn = wxUser.getRealName();
		if(StrUtils.isNotEmpty(rn)) {
			//如果已经能查到realname，直接返回，已经被邀请
			inviteName = rn;
			List<InvitedEntity> tmpLit = wXUserMapper.getInvitedByRealName(rn);
			if(tmpLit .size() > 0){
				dto.setType(tmpLit.get(0).getNvfang());
			}
			dto.setInviteName(inviteName);
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
				
				dto.setType(0);
				dto.setInviteName(inviteName);
			}
			for(InvitedEntity x :all){
				logger.info("要比较了，nickName=" + nickname + ",invitenick=" + x.getInvitedNick());
				if(x.getInvitedNick().equals(nickname)) {
					//在被邀请名单中，需要
					logger.info("匹配到名字相同了，有被邀请到");
					wXUserMapper.updateWxUserRealName(token,x.getRealName());
					inviteName = x.getRealName();
					dto.setType(x.getNvfang());
					dto.setInviteName(inviteName);
				}
			};
		}
		wXUserMapper.updateWxUser(token, nickname, JSONUtils.Obj2Json(userinfo));
		if(StringUtils.isEmpty(dto.getInviteName())){
			dto.setInviteName("");
		}
		if(dto.getType() == 1){
			dto.setAddress(ADDR_LIANG);
		}else{
			dto.setAddress(ADDR_CAO);
		}
		return dto;
	}
	
	
	
//	public static void main(String[] args) {
//		String s = "应帅";
//		String s2 = "应帅";
//		if(s.equals(s2)) {
//			System.out.println(true);
//		}else {
//			System.out.println(false);
//		}
//	}
	public final static String ADDR_CAO = "地点：湖州市南浔区千金镇朝阳村港北43号";
	public final static String ADDR_LIANG = "地点：湖州市乌程大酒店（儿童公园对面）百合厅";

	@Override
	public void addMsg(String token, String msg) {
		UserMsgEntity ett = new UserMsgEntity();
		ett.setWxToken(token);
		ett.setMsg(msg);
		msgMapper.insert(ett);
	}


	@Override
	public List<MsgDTO> listMsg(MsgListRequest req) {
		List<MsgEntityDTO> msgs = msgMapper.list(req.getLastid(), req.getSize());
		
		return null;
	}

}

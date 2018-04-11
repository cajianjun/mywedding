package com.cjj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.cjj.entitys.UserMsgEntity;

public interface MsgMapper {
	@Insert("INSERT INTO user_msg (wx_token,msg) "
			  + "VALUES (#{wxToken},#{msg})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	Long insert(UserMsgEntity entity);
}

package com.cjj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cjj.entitys.WxUserEntity;

public interface WXUserMapper {
	@Select("SELECT * FROM wx_user WHERE id=#{id}")
	WxUserEntity getOneById(@Param("id") Long id);
	
	@Insert("INSERT INTO wx_user (wx_nickname,wx_token,wx_userinfo_json) "
			  + "VALUES (#{wxNickname},#{wxToken},#{wxUserinfoJson})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	Long insert(WxUserEntity entity);
}

package com.cjj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cjj.entitys.InvitedEntity;
import com.cjj.entitys.WxUserEntity;

public interface WXUserMapper {
	@Select("SELECT * FROM wx_user WHERE id=#{id}")
	WxUserEntity getOneById(@Param("id") Long id);
	
	@Select("SELECT * FROM wx_user WHERE wx_token=#{wx_token}")
	WxUserEntity getOneByToken(@Param("wx_token") String wx_token);
	
	@Insert("INSERT INTO wx_user (wx_nickname,wx_token,wx_userinfo_json) "
			  + "VALUES (#{wxNickname},#{wxToken},#{wxUserinfoJson})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	Long insert(WxUserEntity entity);
	
	@Update("UPDATE wx_user SET wx_nickname=#{wx_nickname},wx_userinfo_json=#{wx_userinfo_json} where wx_token=#{wx_token}")
	void updateWxUser(@Param("wx_token") String token,@Param("wx_nickname") String nickname,@Param("wx_userinfo_json") String json);
	
	@Update("UPDATE wx_user SET real_name=#{real_name} where wx_token=#{wx_token}")
	void updateWxUserRealName(@Param("wx_token") String token,@Param("real_name") String real_name);
	
	@Select("SELECT * FROM invited ")
  	List<InvitedEntity> getAllInvited();
}

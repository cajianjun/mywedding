package com.cjj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cjj.dto.MsgEntityDTO;
import com.cjj.entitys.UserMsgEntity;

public interface MsgMapper {
	@Insert("INSERT INTO user_msg (wx_token,msg) "
			  + "VALUES (#{wxToken},#{msg})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	Long insert(UserMsgEntity entity);
	
	@Select("SELECT a.*,b.wx_userinfo_json,b.real_name FROM user_msg a,wx_user b where a.wx_token = b.wx_token and a.id < #{lastid} order by a.id DESC limit 0,#{size}")
  	List<MsgEntityDTO> list(@Param("lastid") Integer lastid,@Param("size") Integer size);
}

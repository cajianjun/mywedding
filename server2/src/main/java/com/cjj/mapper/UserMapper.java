package com.cjj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cjj.entitys.UserEntity;

public interface UserMapper {
	@Select("SELECT * FROM user WHERE id=#{id}")
  	UserEntity getOneById(@Param("id") Long id);
	
	@Insert("INSERT INTO user (wx_token,real_name) "
			  + "VALUES (#{wxToken},#{realName})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	Long insert(UserEntity order);
}

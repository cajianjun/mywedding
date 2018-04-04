package com.cjj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cjj.entitys.PicGroupEntity;
import com.cjj.entitys.PicsEntity;

public interface PicMapper {
	@Select("SELECT * FROM pic_group ")
	List<PicGroupEntity> getGroup();
	
	@Select("SELECT * FROM pics WHERE group_id=#{groupId}")
	List<PicsEntity> getPicsByGroupId(@Param("groupId") int groupId);
}

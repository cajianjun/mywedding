package com.cjj.wedding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjj.dto.PicPageGroupDTO;
import com.cjj.entitys.PicGroupEntity;
import com.cjj.entitys.PicsEntity;
import com.cjj.mapper.PicMapper;
import com.cjj.wedding.service.PicService;

@Service
public class PicServiceImpl implements PicService{
	
	@Autowired
	private PicMapper picMapper;
	
	@Override
	public List<PicPageGroupDTO> getGroup() {
		List<PicGroupEntity> entity = picMapper.getGroup();
		List<PicPageGroupDTO> dtos = new ArrayList<PicPageGroupDTO>();
		entity.forEach(x->{
			PicPageGroupDTO dto = new PicPageGroupDTO();
			dto.setGroupId(x.getId());
			dto.setGroupName(x.getPageName());
			dto.setPagePic(x.getMainPage());
			dtos.add(dto);
		});
		return dtos;
	}

	@Override
	public List<String> getPics(int groupId) {
		
		List<PicsEntity> etts = picMapper.getPicsByGroupId(groupId);
		List<String> pics = new ArrayList<String>();
		etts.forEach(x->{
			pics.add(x.getPicUrl());
		});
		return pics;
	}

}

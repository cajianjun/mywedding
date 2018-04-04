package com.cjj.wedding.service;

import java.util.List;

import com.cjj.dto.PicPageGroupDTO;

public interface PicService {
	public List<PicPageGroupDTO> getGroup() ;
	public List<String> getPics(int groupId);
}

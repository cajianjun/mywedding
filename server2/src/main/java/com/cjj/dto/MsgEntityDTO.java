package com.cjj.dto;

import java.util.Date;

public class MsgEntityDTO {
	private Integer id;
	private String wxToken;
	private String msg;
	private Date gmtCreate;
	private String wxUserinfoJson;
	private String readName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWxToken() {
		return wxToken;
	}
	public void setWxToken(String wxToken) {
		this.wxToken = wxToken;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getWxUserinfoJson() {
		return wxUserinfoJson;
	}
	public void setWxUserinfoJson(String wxUserinfoJson) {
		this.wxUserinfoJson = wxUserinfoJson;
	}
	public String getReadName() {
		return readName;
	}
	public void setReadName(String readName) {
		this.readName = readName;
	}
	
}

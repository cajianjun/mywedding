package com.cjj.entitys;
import java.util.Date;
public class WxUserEntity{
	private Integer id;
	private String wxNickname;
	private String wxToken;
	private String wxUserinfoJson;
	private Date joinTime;
	private String realName;
	public WxUserEntity(){}
	public WxUserEntity(Integer id,String wxNickname,String wxToken,String wxUserinfoJson,Date joinTime,String realName){
		this();
		this.id=id;
		this.wxNickname=wxNickname;
		this.wxToken=wxToken;
		this.wxUserinfoJson=wxUserinfoJson;
		this.joinTime=joinTime;
		this.realName=realName;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setWxNickname(String wxNickname){this.wxNickname=wxNickname;}
	public String getWxNickname(){return wxNickname;}
	public void setWxToken(String wxToken){this.wxToken=wxToken;}
	public String getWxToken(){return wxToken;}
	public void setWxUserinfoJson(String wxUserinfoJson){this.wxUserinfoJson=wxUserinfoJson;}
	public String getWxUserinfoJson(){return wxUserinfoJson;}
	public void setJoinTime(Date joinTime){this.joinTime=joinTime;}
	public Date getJoinTime(){return joinTime;}
	public void setRealName(String realName){this.realName=realName;}
	public String getRealName(){return realName;}
}
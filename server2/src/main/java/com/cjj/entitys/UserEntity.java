package com.cjj.entitys;
public class UserEntity{
	private Integer id;
	private String wxToken;
	private String realName;
	public UserEntity(){}
	public UserEntity(Integer id,String wxToken,String realName){
		this();
		this.id=id;
		this.wxToken=wxToken;
		this.realName=realName;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setWxToken(String wxToken){this.wxToken=wxToken;}
	public String getWxToken(){return wxToken;}
	public void setRealName(String realName){this.realName=realName;}
	public String getRealName(){return realName;}
}
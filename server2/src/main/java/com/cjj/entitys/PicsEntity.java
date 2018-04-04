package com.cjj.entitys;
public class PicsEntity{
	private Integer id;
	private String picUrl;
	private Integer groupId;
	public PicsEntity(){}
	public PicsEntity(Integer id,String picUrl,Integer groupId){
		this();
		this.id=id;
		this.picUrl=picUrl;
		this.groupId=groupId;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setPicUrl(String picUrl){this.picUrl=picUrl;}
	public String getPicUrl(){return picUrl;}
	public void setGroupId(Integer groupId){this.groupId=groupId;}
	public Integer getGroupId(){return groupId;}
}
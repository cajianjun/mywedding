package com.cjj.entitys;
public class PicsEntity{
	private Integer id;
	private String picUrl;
	public PicsEntity(){}
	public PicsEntity(Integer id,String picUrl){
		this();
		this.id=id;
		this.picUrl=picUrl;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setPicUrl(String picUrl){this.picUrl=picUrl;}
	public String getPicUrl(){return picUrl;}
}
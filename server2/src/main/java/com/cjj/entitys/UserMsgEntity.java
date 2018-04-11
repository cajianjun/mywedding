package com.cjj.entitys;
import java.util.Date;
public class UserMsgEntity{
	private Integer id;
	private String wxToken;
	private String msg;
	private Date gmtCreate;
	public UserMsgEntity(){}
	public UserMsgEntity(Integer id,String wxToken,String msg,Date gmtCreate){
		this();
		this.id=id;
		this.wxToken=wxToken;
		this.msg=msg;
		this.gmtCreate=gmtCreate;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setWxToken(String wxToken){this.wxToken=wxToken;}
	public String getWxToken(){return wxToken;}
	public void setMsg(String msg){this.msg=msg;}
	public String getMsg(){return msg;}
	public void setGmtCreate(Date gmtCreate){this.gmtCreate=gmtCreate;}
	public Date getGmtCreate(){return gmtCreate;}
}
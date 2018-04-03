package com.cjj.entitys;
public class InvitedEntity{
	private Integer id;
	private String invitedNick;
	private String realName;
	public InvitedEntity(){}
	public InvitedEntity(Integer id,String invitedNick,String realName){
		this();
		this.id=id;
		this.invitedNick=invitedNick;
		this.realName=realName;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setInvitedNick(String invitedNick){this.invitedNick=invitedNick;}
	public String getInvitedNick(){return invitedNick;}
	public void setRealName(String realName){this.realName=realName;}
	public String getRealName(){return realName;}
}
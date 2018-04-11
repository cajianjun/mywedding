package com.cjj.entitys;
public class InvitedEntity{
	private Integer id;
	private String invitedNick;
	private String realName;
	private Integer nvfang;
	public InvitedEntity(){}
	public InvitedEntity(Integer id,String invitedNick,String realName,Integer nvfang){
		this();
		this.id=id;
		this.invitedNick=invitedNick;
		this.realName=realName;
		this.nvfang=nvfang;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setInvitedNick(String invitedNick){this.invitedNick=invitedNick;}
	public String getInvitedNick(){return invitedNick;}
	public void setRealName(String realName){this.realName=realName;}
	public String getRealName(){return realName;}
	public void setNvfang(Integer nvfang){this.nvfang=nvfang;}
	public Integer getNvfang(){return nvfang;}
}
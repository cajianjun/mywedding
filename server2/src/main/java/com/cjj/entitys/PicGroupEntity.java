package com.cjj.entitys;
public class PicGroupEntity{
	private Integer id;
	private String mainPage;
	private String pageName;
	public PicGroupEntity(){}
	public PicGroupEntity(Integer id,String mainPage,String pageName){
		this();
		this.id=id;
		this.mainPage=mainPage;
		this.pageName=pageName;
	}
	public void setId(Integer id){this.id=id;}
	public Integer getId(){return id;}
	public void setMainPage(String mainPage){this.mainPage=mainPage;}
	public String getMainPage(){return mainPage;}
	public void setPageName(String pageName){this.pageName=pageName;}
	public String getPageName(){return pageName;}
}
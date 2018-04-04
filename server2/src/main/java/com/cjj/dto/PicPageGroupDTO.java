package com.cjj.dto;

public class PicPageGroupDTO {
	private int groupId;
	private String groupName;
	private String pagePic;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPagePic() {
		return pagePic;
	}
	public void setPagePic(String pagePic) {
		this.pagePic = pagePic;
	}
	public PicPageGroupDTO() {
		super();
	}
}

package com.cjj.request;

public class MsgListRequest {
	private Integer lastid;
	private Integer size;
	public Integer getLastid() {
		return lastid;
	}
	public void setLastid(Integer lastid) {
		this.lastid = lastid;
	}
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public MsgListRequest() {
		super();
	}
	
}

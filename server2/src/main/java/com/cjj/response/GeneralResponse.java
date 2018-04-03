package com.cjj.response;

import com.cjj.common.Constants;
import com.cjj.common.ErrorCode;

public class GeneralResponse {
	private Object data;
	private Integer errCode;

	public GeneralResponse(Object data, Integer errCode) {
		super();
		this.errCode = errCode;
		this.data = data;
	}
	
	public static GeneralResponse SUCCESS() {
		return new GeneralResponse(Constants.TASK_SUCCESS, ErrorCode.OK);
	}
	
	public static GeneralResponse SUCCESS(Object data) {
		return new GeneralResponse(data, ErrorCode.OK);
	}
	
	public static GeneralResponse FAILED() {
		return new GeneralResponse(Constants.TASK_FAILED, ErrorCode.FAIL);
	}
	
	public static GeneralResponse FAILED(Object data) {
		return new GeneralResponse(data, ErrorCode.FAIL);
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GeneralResponse [data=" + data.toString() + ", errCode=" + errCode + "]";
	}
	
}


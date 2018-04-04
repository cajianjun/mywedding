package com.cjj.request;

public class DataRequest extends TokenRequest{
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataRequest [data=" + data + ", token=" + token + "]";
	}

	public DataRequest() {
		super();
	}

	
	
}

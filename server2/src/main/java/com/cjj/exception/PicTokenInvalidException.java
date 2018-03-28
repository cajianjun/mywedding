package com.cjj.exception;

/**
 * token对应图片获取不到的报错
 *
 */
public class PicTokenInvalidException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PicTokenInvalidException() {
		super("pic token invalid");
	}
	
	public PicTokenInvalidException(String msg) {
		super(msg);
	}
	
	public PicTokenInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}


}

package com.cjj.common;

public class WXConsts {
	public final static String APPID = "wx2543cc242d38f812";
	public final static String SECRET = "f37489281787ab0719311c5fa314d899";
	public final static String WX_TOKEN_URL_PREFIX = "https://api.weixin.qq.com/sns/jscode2session?appid=" + 
			APPID  + "&secret=" + SECRET
			+ "&grant_type=authorization_code&js_code=";
}

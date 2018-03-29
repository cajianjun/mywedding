package com.cjj.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	
	/**
	 * as method name  ,json string to object
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T json2Obj(String json,Class<T> c) {
		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			t = mapper.readValue(json,c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> T json2Obj(String json,Class<T> c,ObjectMapper mapper) {
		T t = null;
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			t = mapper.readValue(json,c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	
	/**
	 * as method name  ,json object to String
	 * @param obj
	 * @return
	 */
	public static String  Obj2Json(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String s ="";
		try {
			s = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public static String  Obj2Json(Object obj,ObjectMapper mapper) {
		String s ="";
		try {
			s = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}

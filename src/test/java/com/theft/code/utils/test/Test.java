package com.theft.code.utils.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.theft.code.utils.date.DateCalculateUtil;
import com.theft.code.utils.encrypt.Base64Util;
import com.theft.code.utils.encrypt.EncryptUtil;
import com.theft.code.utils.http.HttpToolKit;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(DateCalculateUtil.getDayOfWeek(-1, 2, 1));
		System.out.println(DateCalculateUtil.getDayOfWeek(0, 2, 1));
		System.out.println(DateCalculateUtil.getDayOfWeek(1, 2, 1));

		System.out.println(DateCalculateUtil.getDayOfWeek(-1, 2, 2));
		System.out.println(DateCalculateUtil.getDayOfWeek(0, 2, 2));
		System.out.println(DateCalculateUtil.getDayOfWeek(1, 2, 2));
		
		EncryptUtil eu = new EncryptUtil("123456", null, null);
		System.out.println(eu.encodeBySalt());
		System.out.println("e10adc3949ba59abbe56e057f20f883e".equals(eu.encodeBySalt()));
		
		System.out.println(Base64Util.encode("123456"));
		System.out.println(Base64Util.decode("MTIzNDU2"));
		
		System.out.println(DateCalculateUtil.getCurrentYear(new Date()));
		System.out.println(DateCalculateUtil.getCurrentMonth(new Date()));
		System.out.println(DateCalculateUtil.getDayOfMonth(new Date()));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("momentId", "1");
		params.put("password", "123456");
		params.put("userId", "1");
		params.put("content", "反反复复");
		params.put("isDisplay", "1");
		params.put("addType", "1");
		params.put("addIndexs", "1,2,3");
		params.put("addUrls", "fdf单独df,12单独3123,4恩恩4444");
		System.out.println(HttpToolKit.doPost("http://localhost:8080/login", params, "UTF-8"));
	}
	
}

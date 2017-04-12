package com.theft.code.utils.test;

import java.util.Date;

import com.theft.code.utils.date.DateCalculateUtil;
import com.theft.code.utils.encrypt.Base64Util;
import com.theft.code.utils.encrypt.EncryptUtil;

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
		System.out.println("ec9911a53dc9a443918b39656fa9518a".equals(eu.encodeBySalt()));
		
		System.out.println(Base64Util.encode("123456"));
		System.out.println(Base64Util.decode("MTIzNDU2"));
		
		System.out.println(DateCalculateUtil.getCurrentYear(new Date()));
		System.out.println(DateCalculateUtil.getCurrentMonth(new Date()));
		System.out.println(DateCalculateUtil.getDayOfMonth(new Date()));
	}
	
}

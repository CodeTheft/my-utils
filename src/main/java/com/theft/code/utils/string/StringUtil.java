package com.theft.code.utils.string;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str 传入字符串，null或''，均为空
	 * @return true空， false非空
	 */
	public static boolean strIsNull(String str) {
		return str == null || str.isEmpty();
	}
	
}

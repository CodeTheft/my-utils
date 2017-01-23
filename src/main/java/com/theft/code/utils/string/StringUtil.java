package com.theft.code.utils.string;

/**
 * 字符串工具类
 * @author chufei
 * @date 2017年1月22日
 */
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

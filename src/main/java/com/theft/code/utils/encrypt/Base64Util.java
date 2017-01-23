package com.theft.code.utils.encrypt;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.theft.code.utils.string.StringUtil;

/**
 * base64加密解密工具类
 * 建议MD5或sha1加盐用base64生成
 * @author chufei
 * @date 2017年1月23日
 */
public class Base64Util {

	/**
	 * base64加密
	 * @param inputText 被加密字符串
	 * @return
	 */
	public static String encode(String inputText) {
		if (StringUtil.strIsNull(inputText)) {
			return null;
		}
		
		try {
			return Base64.encodeBase64String(inputText.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * basr64解密
	 * @param outputText 被解密字符串
	 * @return
	 */
	public static String decode(String outputText) {
		if (StringUtil.strIsNull(outputText)) {
			return null;
		}
		
		try {
			return new String(Base64.decodeBase64(outputText), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
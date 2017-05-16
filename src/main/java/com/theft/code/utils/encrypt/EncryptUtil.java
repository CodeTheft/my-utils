package com.theft.code.utils.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.theft.code.utils.string.StringUtil;

/**
 * 加密工具类，支持MD5,SHA1加盐加密
 * @author chufei
 * @date 2017年1月22日
 */
public class EncryptUtil {

	/**
	 * MD5加密
	 */
	public static final String ENCRYPT_MD5 = "MD5";
	
	/**
	 * SHA1加密
	 */
	public static final String ENCRYPT_SHA1 = "SHA1";
	
	/**
	 * 被加密字符串
	 */
	private String inputText = "";
	
	/**
	 * 加盐
	 */
	private String salt = "";
	
	/**
	 * 加密方式，MD5或SHA1
	 */
	private String algorithm = ENCRYPT_MD5;

	public EncryptUtil() {}
	
	public EncryptUtil(String inputText, String salt, String algorithm) {
		this.inputText = inputText;
		this.salt = salt;
		this.algorithm = StringUtil.strIsNull(algorithm) ? ENCRYPT_MD5 : algorithm;
	}
	
	/**
	 * 加盐字符串加密
	 * @throws Exception 
	 */
	public String encodeBySalt() {
		if (StringUtil.strIsNull(inputText)) {
			return null;
		}

		if (StringUtil.strIsNull(salt)) {
			return encode(inputText);
		}
		
		return encode(inputText + "{" + salt + "}");
	}
	
	// 字符串加密
	private String encode(String inputText) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
			digest.update(inputText.getBytes());
			
			// 2017/5/16 update
			return new BigInteger(1, digest.digest()).toString(16);
			
			// 将16为字节数组转换成32为字符串
			/* byte[] buffers = digest.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < buffers.length; i++) {
				// 补位为16进制
				String hs = Integer.toHexString(buffers[i] & 0xFF);
				if (hs.length() < 2) {
					sb.append("0");
				}
				sb.append(hs);
			}
			return sb.toString();*/
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

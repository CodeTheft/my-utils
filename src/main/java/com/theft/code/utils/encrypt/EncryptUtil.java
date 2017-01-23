package com.theft.code.utils.encrypt;

import java.security.MessageDigest;

import com.theft.code.utils.string.StringUtil;

/**
 * 加密工具类，支持MD5,SHA1加盐加密
 * @author chufei
 * @date 2017年1月22日
 */
public class EncryptUtil {

	private String inputText = "";
	private String salt = "";
	private String algorithm = "MD5";

	public EncryptUtil() {}
	
	public EncryptUtil(String inputText, String salt, String algorithm) {
		this.inputText = inputText;
		this.salt = salt;
		this.algorithm = algorithm;
	}
	
	/**
	 * 加盐字符串加密
	 * @param inputText 被加密字符串
	 * @param salt 盐, 不加盐为null
	 * @return 
	 * @throws Exception 
	 */
	public String encodeBySalt() throws Exception {
		if (StringUtil.strIsNull(inputText)) {
			return null;
		}

		if (StringUtil.strIsNull(salt)) {
			return encode(inputText);
		}
		return encode(inputText + "{" + salt + "}");
	}
	
	// 字符串加密
	private String encode(String inputText) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(inputText.getBytes());
		byte[] buffers = digest.digest();

		// 将16为字节数组转换成32为字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buffers.length; i++) {
			// 补位为16进制
			String hs = Integer.toHexString(buffers[i] & 0xFF);
			if (hs.length() < 2) {
				sb.append("0");
			}
			sb.append(hs);
		}
		return sb.toString();
	}
	
}

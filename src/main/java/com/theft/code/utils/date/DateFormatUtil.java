package com.theft.code.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.theft.code.utils.string.StringUtil;

/**
 * 日期格式转换工具类
 * @author chufei
 * @date 2017年1月22日
 */
public class DateFormatUtil {

	public static final String DEFAULT_DATE_FORMAT_STRING_PATTERN = "yyyy-MM-dd";
	
	/**
	 * 将日期格式转换成字符串
	 * @param date 转换的时间
	 * @param pattern 转换格式，默认yyyy-MM-dd
	 * @return
	 */
	public static String formatDate2String(Date date, String pattern) {
		pattern = StringUtil.strIsNull(pattern) ? DEFAULT_DATE_FORMAT_STRING_PATTERN : pattern;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 将字符串转换成日期格式
	 * @param s_date 日期字符串
	 * @param pattern 转换格式，默认yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date formatString2Date(String dateStr, String pattern) throws ParseException {
		pattern = StringUtil.strIsNull(pattern) ? DEFAULT_DATE_FORMAT_STRING_PATTERN : pattern;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(dateStr);
	}
	
	/**
	 * 将时间戳转换成字符串格式
	 * @param timestamp 时间戳，单位：毫秒
	 * @param pattern 转换格式，默认yyyy-MM-dd
	 * @return
	 */
	public static String formatTimestamp2String(long timestamp, String pattern) {
		pattern = StringUtil.strIsNull(pattern) ? DEFAULT_DATE_FORMAT_STRING_PATTERN : pattern;
		return formatDate2String(new Date(timestamp), pattern);
	}
	
}

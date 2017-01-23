package com.theft.code.utils.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间计算工具类
 * @author chufei
 * @date 2017年1月22日
 */
public class DateCalculateUtil {

	/**
	 * 中国星期类型，周一是第一天
	 */
	public static final int WEEK_TYPE_CN = 1;

	/**
	 * 美国星期类型，周日是第一天
	 */
	public static final int WEEK_TYPE_US = 2;
	
	/**
	 * 获取当天的第一个时间，即00:00:00
	 * @return
	 */
	public static Date getBeginTimeOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取当天的最后一个时间，即23:59:59
	 * @return
	 */
	public static Date getLastTimeOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 给指定日期增减时间
	 * @param date 指定日期
	 * @param field 加的时间类型
	 * @param amount 大于0增加时间，小于0减少时间
	 * @return
	 */
	public static Date addDays(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	/**
	 * 获取某月的第一天的0点
	 * @param amount 0表示当月，1表示下月，-1表示上月，以此类推。。。
	 * @return
	 */
	public static Date getFirstDayOfMonth(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, amount);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某月的最后一天的0点
	 * @param amount 0表示当月，1表示下月，-1表示上月，以此类推。。。
	 * @return
	 */
	public static Date getLastDayOfMonth(int amount) {
		return addDays(getFirstDayOfMonth(amount + 1), Calendar.DATE, -1);
	}
	
	/**
	 * 获取某一周的某一天
	 * @param week 0表示当周，1表示下周，-1表示上周，以此类推。。。
	 * @param day 表示第几天，与星期类型结合使用
	 * @param weekType 星期类型，WEEK_TYPE_CN-中国星期，WEEK_TYPE_US-美国星期
	 * @return
	 */
	public static Date getDayOfWeek(int week, int day, int weekType) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_MONTH, week);
		calendar.set(Calendar.DAY_OF_WEEK, day);
		Date dayOfWeek = calendar.getTime();
		return weekType == WEEK_TYPE_CN ? addDays(dayOfWeek, Calendar.DATE, 1) : dayOfWeek;
	}
	
}

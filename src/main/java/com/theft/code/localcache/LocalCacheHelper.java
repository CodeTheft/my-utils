package com.theft.code.localcache;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存帮助类
 * @author chufei
 * @date 2017年12月25日
 */
public class LocalCacheHelper {

	private static final long SECOND_TIME = 1000;
	
	private static final Timer TIMER = new Timer();
	
	private static final Map<String, Object> MAP = new ConcurrentHashMap<>();
	
	private LocalCacheHelper() {}
	
	/**
	 * 设置永久缓存
	 * @param key 缓存key名称
	 * @param value 缓存值
	 */
	public static void set(String key, Object value) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}
		MAP.put(key, value);
	}
	
	/**
	 * 设置有效期缓存
	 * @param key 缓存key名称
	 * @param value 缓存值
	 * @param expires 缓存后有效时间：秒
	 */
	public static void setExpire(String key, Object value, int expires) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}
		
		if (expires <= 0) {
			throw new IllegalArgumentException("Parameter expires must larger than 0!");
		}
		
		MAP.put(key, value);
		TIMER.schedule(new CleanCacheTask(key), expires * SECOND_TIME);
	}

	/**
	 * 设置有效期缓存
	 * @param key 缓存key名称
	 * @param value 缓存值
	 * @param expireTime 缓存有效截止时间
	 */
	public static void setAtExpireTime(String key, Object value, Date expireTime) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}
		MAP.put(key, value);
		TIMER.schedule(new CleanCacheTask(key), expireTime);
	}
	
	/**
	 * 获取缓存值
	 * @param key 缓存key名称
	 * @return 缓存值
	 */
	public static Object get(String key) {
		return MAP.get(key);
	}
	
	/**
	 * 判断是否存在该缓存
	 * @param key 缓存key名称
	 * @return true: 存在，false: 不存在
	 */
	public static boolean containsKey(String key) {
		return MAP.containsKey(key);
	}
	
	/**
	 * 获取缓存大小
	 * @return
	 */
	public static int size() {
		return MAP.size();
	}
	
	/**
	 * 删除缓存
	 * @param key 缓存key名称
	 */
	public static void remove(String key) {
		MAP.remove(key);
	}
	
	/**
	 * 清除所有缓存
	 */
	public static void clear() {
		if (size() > 0) {
			MAP.clear();
		}
		TIMER.cancel();
	}
	
	/**
	 * 清理缓存定时任务
	 */
	static class CleanCacheTask extends TimerTask {
		
		private String key;
		
		private CleanCacheTask(String key) {
			this.key = key;
		}
		
		@Override
		public void run() {
			LocalCacheHelper.remove(key);
		}
		
	}
}

package com.theft.code.cache.localcache;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * 本地缓存帮助类
 * 
 * @author chufei
 * @date 2017年12月25日
 */
public class LocalCacheHelper {

	public static String print() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, CacheEntity> entry : MAP.entrySet()) {
			builder.append(entry.getKey() + "=" + entry.getValue().value + "(" + entry.getValue().expireTime + "), ");
		}
		return builder.toString();
	}

	/**
	 * 将单位:秒转换为单位:毫秒
	 */
	private static final long SECONDS_TIME = 1000L;

	private static final Map<String, CacheEntity> MAP = new ConcurrentHashMap<>();

	/**
	 * 定义一个ScheduledThreadPoolExecutor，每500毫秒执行清理过期缓存
	 */
	private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	static {
		scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder()
				.namingPattern("localcache-scheduled-thread-pool-%d").daemon(true).build());
		scheduledThreadPoolExecutor.scheduleWithFixedDelay(new CleanCacheTask(), 100, 500, TimeUnit.MILLISECONDS);
	}

	private LocalCacheHelper() {
	}

	/**
	 * 设置永久缓存
	 * 
	 * @param key
	 *            缓存key名称
	 * @param value
	 *            缓存值
	 */
	public static void set(String key, Object value) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}
		MAP.put(key, new CacheEntity(value, -1L));
	}

	/**
	 * 设置有效期缓存
	 * 
	 * @param key
	 *            缓存key名称
	 * @param value
	 *            缓存值
	 * @param expires
	 *            缓存后有效时间：秒
	 */
	public static void setExpire(String key, Object value, int expires) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}

		if (expires <= 0) {
			throw new IllegalArgumentException("Parameter expires must larger than 0!");
		}

		MAP.put(key, new CacheEntity(value, System.currentTimeMillis() + expires * SECONDS_TIME));
	}

	/**
	 * 设置有效期缓存
	 * 
	 * @param key
	 *            缓存key名称
	 * @param value
	 *            缓存值
	 * @param expireTime
	 *            缓存有效截止时间
	 */
	public static void setAtExpireTime(String key, Object value, Date expireTime) {
		if (value == null) {
			throw new IllegalArgumentException("Couldn't set null to Local Cache!");
		}

		if (expireTime == null) {
			throw new IllegalArgumentException("Parameter expireTime couldn't null!");
		}

		MAP.put(key, new CacheEntity(value, expireTime.getTime()));
	}

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 *            缓存key名称
	 * @return 缓存值
	 */
	public static Object get(String key) {
		CacheEntity cache = MAP.get(key);
		if (cache == null) {
			return null;
		}
		return cache.value;
	}

	/**
	 * 判断是否存在该缓存
	 * 
	 * @param key
	 *            缓存key名称
	 * @return true: 存在，false: 不存在
	 */
	public static boolean containsKey(String key) {
		return MAP.containsKey(key);
	}

	/**
	 * 获取缓存大小
	 * 
	 * @return
	 */
	public static int size() {
		return MAP.size();
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 *            缓存key名称
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
	}

	/**
	 * 清理缓存定时任务
	 */
	static class CleanCacheTask implements Runnable {

		@Override
		public void run() {
			for (Entry<String, CacheEntity> entry : MAP.entrySet()) {
				CacheEntity cache = entry.getValue();
				if (cache.expireTime == -1L) {
					continue;
				}
				// 如果缓存有效时间小于等于当前时间，则清除
				if (cache.expireTime <= System.currentTimeMillis()) {
					remove(entry.getKey());
				}
			}
		}

	}

	/**
	 * 缓存临时存储对象
	 */
	private static class CacheEntity {
		/**
		 * 缓存值
		 */
		private Object value;
		/**
		 * 缓存有效时间戳
		 */
		private long expireTime;

		private CacheEntity(Object value, long expireTime) {
			this.value = value;
			this.expireTime = expireTime;
		}
	}
}

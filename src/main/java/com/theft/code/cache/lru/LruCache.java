package com.theft.code.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 基于LinkHashMap实现的简单LRU缓存
 * @author chufei
 * 2018年6月26日
 * @param <K>
 * @param <V>
 */
public class LruCache<K, V> {

	private final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int cacheSize;
	
	private LinkedHashMap<K, V> cache;
	
	public LruCache(int cacheSize) {
		this.cacheSize = cacheSize;
		// 根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
		int capacity = (int) Math.ceil(cacheSize / DEFAULT_LOAD_FACTOR) + 1;
		cache = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTOR, true) {
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean removeEldestEntry(Entry<K, V> eldest) {
				return size() > LruCache.this.cacheSize;
			}
		};
	}
	
	public synchronized V get(K key) {
		return cache.get(key);
	}
	
	public synchronized V put(K key, V value) {
		return cache.put(key, value);
	}
	
	public synchronized int size() {
		return cache.size();
	}
	
	public synchronized void clear() {
		cache.clear();
	}
}

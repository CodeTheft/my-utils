package com.theft.code.utils.test;

import com.theft.code.cache.lru.LRUCachePlus;

public class Test {

	public static void main(String[] args) throws Exception {
		LRUCachePlus<Integer, String> cache = new LRUCachePlus<>(3);
		cache.put(1, "1");
		cache.put(2, "2");
		cache.put(3, "3");
		System.out.println(cache.toString());
		cache.put(4, "4");
		System.out.println(cache.toString());
		cache.get(2);
		System.out.println(cache.toString());
	}
	
}

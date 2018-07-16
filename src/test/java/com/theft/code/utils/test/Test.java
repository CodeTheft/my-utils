package com.theft.code.utils.test;

import com.theft.code.cache.localcache.LocalCacheHelper;

/**
 * 测试类
 * 
 * @author chufei 2018年7月14日
 */
public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(LocalCacheHelper.print());
		LocalCacheHelper.set("tom", 1);
		System.out.println(LocalCacheHelper.print());
		LocalCacheHelper.setExpire("jerry", 2, 5);
		System.out.println(LocalCacheHelper.print());
		try {
			Thread.sleep(5500);
			System.out.println("5.5s later, " + LocalCacheHelper.print());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LocalCacheHelper.clear();
		System.out.println(LocalCacheHelper.print());
		LocalCacheHelper.setExpire("jack", 3, 6);
		System.out.println(LocalCacheHelper.print());
		try {
			Thread.sleep(5000);
			System.out.println("5s later, " + LocalCacheHelper.print());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
			System.out.println("5s later, " + LocalCacheHelper.print());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LocalCacheHelper.set("jonh", 4);
		System.out.println(LocalCacheHelper.print());
	}

}

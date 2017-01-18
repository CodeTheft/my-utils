package com.theft.code.utils.test;

import com.theft.code.utils.date.DateCalculateUtils;

public class Test {

	public static void main(String[] args) {
		System.out.println(DateCalculateUtils.getDayOfWeek(-1, 2, 1));
		System.out.println(DateCalculateUtils.getDayOfWeek(0, 2, 1));
		System.out.println(DateCalculateUtils.getDayOfWeek(1, 2, 1));

		System.out.println(DateCalculateUtils.getDayOfWeek(-1, 2, 2));
		System.out.println(DateCalculateUtils.getDayOfWeek(0, 2, 2));
		System.out.println(DateCalculateUtils.getDayOfWeek(1, 2, 2));
	}
	
}

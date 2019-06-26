package com.automation.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RestUtils {
	
	public static String getRandomName() {
		String randomStr=RandomStringUtils.randomAlphanumeric(2);
		String firstName="Chris";
		
		
		return firstName+randomStr;
	}
	
	public static float getRandomSalary() {
		float randomSal=RandomUtils.nextFloat(25000, 89000);
		return randomSal;
	}
	
	public static int getRandomAge() {
		int randomAge=RandomUtils.nextInt(0, 120);
		return randomAge;
	}
}

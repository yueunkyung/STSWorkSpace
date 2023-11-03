package com.shinhan.day01;

public class TVFactory {
	public static TV makeTV(String brand) {
		TV tv = null;
		if (brand.equals("lg")) {
			tv = new LgTV("ggggg");
		} else if(brand.equals("sam")) {
			tv = new SamsungTV();
		}
		return tv;
	}
}

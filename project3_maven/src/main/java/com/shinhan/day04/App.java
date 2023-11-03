package com.shinhan.day04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop1.xml");
		Calculator cal = ctx.getBean("proxyCal", Calculator.class);
		int result = cal.add(99);
		System.out.println("result =>> " + result);
	}
}

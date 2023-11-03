package com.shinhan.day05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop2.xml");
		
		Calculator cal = ctx.getBean("proxyCal", Calculator.class);
		cal.add(1,9);
	}
}

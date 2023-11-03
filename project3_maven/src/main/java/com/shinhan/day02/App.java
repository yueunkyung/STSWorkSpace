package com.shinhan.day02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		//f1();
		//f2();
		//f3();
		f4();
	}

	private static void f4() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di3.xml");
		Person p1 = ctx.getBean("person2", Person.class);
		Person p2 = ctx.getBean("person2", Person.class);
		System.out.println("App.java f4() p2 : " + p2);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1==p2);
		System.out.println(System.identityHashCode(p1));
		System.out.println(System.identityHashCode(p2));
	}

	private static void f3() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di2.xml");
		CarVO c4 = ctx.getBean("car4", CarVO.class);
		System.out.println(c4);
	}

	private static void f2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di2.xml");
		CarVO c1 = (CarVO) ctx.getBean("car1");
		CarVO c2 = ctx.getBean("car1", CarVO.class);
		
		CarVO c3 = (CarVO) ctx.getBean("car2");
		CarVO c4 = ctx.getBean("car2", CarVO.class);
		
		CarVO c5 = (CarVO) ctx.getBean("car3");
		CarVO c6 = ctx.getBean("car3", CarVO.class);
		
		System.out.println(c1==c2);
		System.out.println(c3==c4);
		System.out.println(c5==c6);
		System.out.println(c1);
		System.out.println(c6);
	}

	private static void f1() {
		//App class가 CarVO를 의존한다.
		CarVO c1 = new CarVO();
		CarVO c2 = new CarVO("ABC", 1000);
		System.out.println(c1);
		System.out.println(c2);
	}
}

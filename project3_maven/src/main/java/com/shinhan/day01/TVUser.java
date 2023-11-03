package com.shinhan.day01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TVUser {
	public static void main(String[] args) {
		//f1();
		//f2();
		//f3();
		f4();
	}

	private static void f4() {
		//3.Spring 이용 : POJO(java class) + XML설정 => Spring 객체 생성
		//DI(Dependency Injection) : 의존 관계 주입
		//IoC(Inversion of Control) : 제어의 역전
		
		//ApplicationContext : 사용 전에 load된다.
		ApplicationContext context = new ClassPathXmlApplicationContext("di1.xml");
		
//		TV tv1 = (TV)context.getBean("lg");
//		tv1.powerOn();
//		tv1.powerOff();
//		tv1 = (TV)context.getBean("lg2");
//		tv1.powerOn();
//		tv1.powerOff();
//		tv1 = (TV)context.getBean("ss");
//		tv1.powerOn();
//		tv1.powerOff();
	}

	private static void f3() {
		//3.Spring 이용 : POJO(java class) + XML설정 => Spring 객체 생성
		//DI(Dependency Injection) : 의존 관계 주입
		//IoC(Inversion of Control) : 제어의 역전
		Resource resource = new ClassPathResource("di1.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		TV tv1 = (TV)factory.getBean("lg");
		tv1.powerOn();
		tv1.powerOff();
		tv1 = (TV)factory.getBean("lg2");
		tv1.powerOn();
		tv1.powerOff();
		tv1 = (TV)factory.getBean("ss");
		tv1.powerOn();
		tv1.powerOff();
	}

	private static void f2() {
		//Factory pattern
		TV tv = TVFactory.makeTV("sam");
		tv.powerOn();
		tv.powerOff();
	}

	private static void f1() {
		//의존 관계가 있다.
		//TVUser가 LgTV class를 사용중
		//LgTV 생성이 달라지면 영향을 받는다.
		TV tv =new LgTV("ㅁㅁㅁ");
		tv.powerOn();
		tv.powerOff();
	}
}

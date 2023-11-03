package com.shinhan.day03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shinhan.dto.EmpVO;
import com.shinhan.model.EmpService;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("di4_annotation.xml");
		EmpService service = ctx.getBean("eService", EmpService.class);
		EmpVO emp = service.selectById(100);
		System.out.println(emp);
	}
	
	public static void f1(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("di4_annotation.xml");
		//Person p1= ctx.getBean("person", Person.class);
		//System.out.println(p1);
	
		CarVO car1 = ctx.getBean("carcar", CarVO.class);
		System.out.println(car1);
		car1.setModel("DDD");
		car1.setPrice(2000);
		System.out.println(car1);
	}

}

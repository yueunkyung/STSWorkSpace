package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.dto.Car;

//POJO(Plain Old Java Object)

@Controller
@RequestMapping("/firstzone")
public class SampleController3 {
	
	Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping("/one")
	public void f1() {
		logger.info("��û�ּ� : /firstzone/one...........................");
		///forward => WEB-INF/views/firsezone/one.jsp
	}
	
	@RequestMapping("/two")
	public String f2(Model data) {
		//Model�� page�� data�� �����ϱ� ����
		logger.info("��û�ּ�2 : /firstzone/two...........................");
		data.addAttribute("myname","eunkyung");
		data.addAttribute("myage",20);
		data.addAttribute("mycar",new Car("ABC", 2000));
		return "firstzone/one";
	}
}

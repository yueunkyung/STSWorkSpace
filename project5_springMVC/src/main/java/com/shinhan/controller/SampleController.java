package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Component + Controller 역할(요청과 응답처리)
//요청 => DispatcherServlet이 받아서 요청 주소와 Mapping된 Controller를 찾아서 실행

@Controller
@RequestMapping("/day1")	//1)class level 2)method level
public class SampleController {
	//http://localhost:9090/myapp/hellow1?userid=abc
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	// 1.요청 주소와 페이지 이름이 일치하다면 페이지 이름 return을 생략한다.
	@RequestMapping("/hello1")
	public void method1(@RequestParam("userid") String userid) {
		logger.info("/hello1 이라고 요청함........ userid =>{}", userid);
		//System.out.println("");
		//WEB-INF/views/hello1.jsp
	}
	
	// 2.요청 주소와 페이지 이름이 일치하지 않으면 페이지 이름을 return한다.
	@RequestMapping("/hello2")
	public String method2(@RequestParam("userid") String userid) {
		logger.info("/hello2 이라고 요청함........ userid =>{}", userid);
		return "hello1";
	}
	
	// 3.parameter 이름(@RequestParam("userid"))과 매개변수(String userid) 이름이 같으면 생략 가능.
	@RequestMapping(value="/hello3", method=RequestMethod.POST)
	public String method3(String userid) {
		logger.info("/hello3 이라고 요청함........ userid =>{}", userid);
		return "hello1";
	}
	
	@RequestMapping(value={"/hello4","/hello5"}, method=RequestMethod.GET)
	public String method4(String userid) {
		logger.info("/hello4 /hello5 이라고 요청함........ userid =>{}", userid);
		return "hello1";
	}
	
	// 요청문서의 parameter가 값 일치=>"userid=good", 존재=>"userpass", 존재 안함=>"!email"
	@RequestMapping(value="/hello6", params = {"userid=good","userpass","!email"})
	public String method4(String userid, String userpass, String email2) {
		logger.info("userid={}, userpass={}, email2={}", userid, userpass, email2);
		return "hello1";
	}
	
}

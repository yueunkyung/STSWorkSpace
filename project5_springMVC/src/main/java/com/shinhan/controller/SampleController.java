package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Component + Controller ����(��û�� ����ó��)
//��û => DispatcherServlet�� �޾Ƽ� ��û �ּҿ� Mapping�� Controller�� ã�Ƽ� ����

@Controller
@RequestMapping("/day1")	//1)class level 2)method level
public class SampleController {
	//http://localhost:9090/myapp/hellow1?userid=abc
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	// 1.��û �ּҿ� ������ �̸��� ��ġ�ϴٸ� ������ �̸� return�� �����Ѵ�.
	@RequestMapping("/hello1")
	public void method1(@RequestParam("userid") String userid) {
		logger.info("/hello1 �̶�� ��û��........ userid =>{}", userid);
		//System.out.println("");
		//WEB-INF/views/hello1.jsp
	}
	
	// 2.��û �ּҿ� ������ �̸��� ��ġ���� ������ ������ �̸��� return�Ѵ�.
	@RequestMapping("/hello2")
	public String method2(@RequestParam("userid") String userid) {
		logger.info("/hello2 �̶�� ��û��........ userid =>{}", userid);
		return "hello1";
	}
	
	// 3.parameter �̸�(@RequestParam("userid"))�� �Ű�����(String userid) �̸��� ������ ���� ����.
	@RequestMapping(value="/hello3", method=RequestMethod.POST)
	public String method3(String userid) {
		logger.info("/hello3 �̶�� ��û��........ userid =>{}", userid);
		return "hello1";
	}
	
	@RequestMapping(value={"/hello4","/hello5"}, method=RequestMethod.GET)
	public String method4(String userid) {
		logger.info("/hello4 /hello5 �̶�� ��û��........ userid =>{}", userid);
		return "hello1";
	}
	
	// ��û������ parameter�� �� ��ġ=>"userid=good", ����=>"userpass", ���� ����=>"!email"
	@RequestMapping(value="/hello6", params = {"userid=good","userpass","!email"})
	public String method4(String userid, String userpass, String email2) {
		logger.info("userid={}, userpass={}, email2={}", userid, userpass, email2);
		return "hello1";
	}
	
}

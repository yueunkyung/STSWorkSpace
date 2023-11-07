package com.shinhan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController2 {
	@RequestMapping("/custInfo")
	public ModelAndView f1(String custid, String custname, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		String id = (String)session.getAttribute("login_id");
		String phone = (String)session.getAttribute("login_phone");
		System.out.println("id="+id);
		System.out.println("phone="+phone);
		
		mv.addObject("title", "Spring Framework Àç¹Õ¾î");
		mv.addObject("custid", custid);
		mv.addObject("custname", custname);
		
		mv.setViewName("cust/custinfo");
		return mv;
	}
	
}

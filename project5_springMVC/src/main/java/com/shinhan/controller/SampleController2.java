package com.shinhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController2 {
	@RequestMapping("/custInfo")
	public ModelAndView f1(String custid, String custname) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Spring Framework Àç¹Õ¾î");
		mv.addObject("custid", custid);
		mv.addObject("custname", custname);
		
		mv.setViewName("cust/custinfo");
		return mv;
	}
	
}

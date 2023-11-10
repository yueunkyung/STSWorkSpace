package com.shinhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.model.AccountServiceImpl;

@Controller
public class TransactionTestController {
	@Autowired
	AccountServiceImpl service;
	
	@RequestMapping("/account.do")
	public String test1() {
		service.update();
		return "home";
	}
}

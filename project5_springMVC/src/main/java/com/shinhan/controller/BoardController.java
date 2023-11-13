package com.shinhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//http://localhost:9090/myapp/board

@Controller
//@ResponseBody	//response.getWriter().append("Served at: ")
public class BoardController {
	
	@RequestMapping("/board")
	public String start() {
		return "board/boardStart";
	}
	
	@RequestMapping("/board/insert.do")
	public String insert() {
		return "board/boardInsert";
	}
}

/*
	response.setContentType("text/html; charset=utf-8");
	response.getWriter().append("Served at: ").append(request.getContextPath())
	.append("<br>")
	.append(Arrays.toString(subjects))
	.append("<br>")
	.append(gender);
 */
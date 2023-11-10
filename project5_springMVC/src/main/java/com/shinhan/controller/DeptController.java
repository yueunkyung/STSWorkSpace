package com.shinhan.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.dto.DeptVO;
import com.shinhan.model.DeptService;
import com.shinhan.model.EmpService;

@ControllerAdvice
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	DeptService dService;

	@Autowired
	EmpService eService;
	
	Logger logger = LoggerFactory.getLogger(DeptController.class);

	@GetMapping("/deptList.do")
	public String selectAll(Model model) {
		
		model.addAttribute("dlist", dService.selectAll());
		
		return "dept/deptlist";
	}

	@GetMapping("/deptDetail.do")
	public String selectById(int deptid, Model model) {
		ArrayList<Integer> dlist=new ArrayList<Integer>();
		dlist.add(deptid);
		model.addAttribute("dept", dService.selectById(deptid));
		model.addAttribute("mlist", eService.selectAll(dlist, "%", 0, "1900-01-01"));
		
		return "dept/deptDetail";
	}
	
	@PostMapping("/deptUpdate.do")
	public String deptUpdatePost(DeptVO dept, RedirectAttributes attr) {
		logger.info("수정할 부서정보:{}",dept.toString());
		int result = dService.updateDept(dept);
		System.out.println("resultresult=============================="+result);
		attr.addFlashAttribute("deptMessage", result>0? "수정 성공":"수정 실패");
		
		return "redirect:deptList.do";
	}
	
	@GetMapping("/deptInsert.do")
	public String insertGet(Model model) {
		ArrayList<Integer> dlist=new ArrayList<Integer>();
		dlist.add(0);
		model.addAttribute("mlist",eService.selectAll(dlist, "%", 0, "1999-01-01"));
		return "dept/deptInsert";
	}
	
	@PostMapping("/deptInsert.do")
	public String deptinsertPost(DeptVO dept, RedirectAttributes attr) {
		logger.info("입력할 부서정보:{}",dept.toString());
		int result = dService.insertDept(dept);
		System.out.println("resultresult=============================="+result);
		attr.addFlashAttribute("deptMessage", result>0? "입력 성공":"입력 실패");
		
		return "redirect:deptList.do";
	}
	
	@GetMapping("/deptDelete.do")
	public String deleteGet(int deptid, RedirectAttributes attr) {
		logger.info("삭제할 부서정보:{}", deptid);
		int result = dService.deleteDept(deptid);
		attr.addFlashAttribute("deptMessage", result>0?"삭제성공":"삭제실패");
		
		return "redirect:deptList.do";
	}
	
	
}

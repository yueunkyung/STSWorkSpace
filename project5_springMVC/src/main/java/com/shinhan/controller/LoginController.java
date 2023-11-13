package com.shinhan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.dto.CustomerVO;
import com.shinhan.dto.UserVO;
import com.shinhan.model.CustomerService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/auth")
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	CustomerService custService;
	
	@RequestMapping("/login.do")
	public void loginGet(HttpServletRequest request, Model model) {
		//forward => /WEB-INF/views/auth/login.jsp
		Map<String, ?> flashData = RequestContextUtils.getInputFlashMap(request);
		if(flashData != null) {
			for(String key:flashData.keySet()) {
				logger.info(key+"==>>>"+flashData.get(key));
				model.addAttribute(key, flashData.get(key));
			}
		}
		
	}
	
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public String loginPost(@RequestParam("cust_id") String id, String password, HttpServletRequest request, HttpSession session, RedirectAttributes attr) {
		logger.info("��û�ּ�: {}", request.getRequestURI());
		logger.info("��û���: {}", request.getMethod());
		logger.info("��û�� ��� ip: {}", request.getRemoteAddr());
		logger.info("�Ķ����password: {}", request.getParameter("password"));
		logger.info("cust_id: {}", id);
		logger.info("password: {}", password);

		CustomerVO cust = custService.loginCheck(id, password);
		
		//���ǿ� �����ϱ�
		session.setAttribute("loginCust", cust);
		session.setAttribute("login_id", id);
		session.setAttribute("login_phone", password);
		
		if(cust==null) {
			attr.addFlashAttribute("message","�α��� ����....... �ٽ� �Է��ϱ�");
			return "redirect:/auth/login.do"; //���û�ϱ� ���� redirect: �� ���δ�. �ٽ� �α���
		} else {
			return "redirect:/board"; //�����Ϸ� ����.
		}
	}
	/*
	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public String signUp(UserVO user, @RequestParam(value="address", required=false) String address) { //UserVO�� default ������, setter
		//@RequestParam : request.getParameter("")
		//default�� required = true ��, �ʼ��̴�. �Ķ���Ͱ� ������ �����̴�.
		logger.info("�Էµ� ����� ����: {}", user);
		logger.info("type='hidden' address: {}", address);
		return "redirect:/firstzone/two"; 
	}
	*/
	
	@RequestMapping(value="/signUp2.do", method=RequestMethod.POST)
	public String signUp2(@RequestParam Map<String, String> user, @RequestParam(value="address", required=false) String address) { //UserVO�� default ������, setter
		//@RequestParam : request.getParameter(""), map�϶� �����Ұ�
		//default�� required = true ��, �ʼ��̴�. �Ķ���Ͱ� ������ �����̴�.
		System.out.println(user.get("username"));
		logger.info("�Էµ� ����� ����: {}", user);
		logger.info("type='hidden' address: {}", address);
		return "redirect:/firstzone/two"; 
	}
/*
	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public String signUp(@ModelAttribute("user") UserVO user
						, @RequestParam(value="address"
						, required=false) String address
						, Model model) { 
		//UserVO�� default ������, setter
		//@RequestParam : request.getParameter("")
		//default�� required = true ��, �ʼ��̴�. �Ķ���Ͱ� ������ �����̴�.
		//@ModelAttribute Model�� �����Ͽ� view���� ����ϵ��� �Ѵ�.
		logger.info("�Էµ� ����� ����: {}", user);
		logger.info("type='hidden' address: {}", address);
		
		model.addAttribute("user2", user);
		model.addAttribute("message", "ȸ������ ���� !!!!");

		return "auth/signupResult"; //forward�ϱ�
	}
*/
	@PostMapping(value="/signUp.do")
	public String signUp(CustomerVO cust, Model model) {
		//logger.info�� ���ڸ� �Է� �����Ͽ�, ��ü�� toString() ó���ؾ���.
		logger.info("param�� Ȯ��"+cust.toString());
		
		int result = custService.register(cust);
		model.addAttribute("message", result>0?"ȸ������ ����":"ȸ������ ����");
		return "auth/signupResult"; //forward�ϱ�
	}

	@RequestMapping("/test1.do")
	public ModelAndView test1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hobby","����");
		mv.addObject("myname","����");
		mv.setViewName("firstzone/one");
		
		return mv;
	}
	
	@RequestMapping("/test2.do")
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView("firstzone/one");
		mv.addObject("hobby","����2");
		mv.addObject("myname","����2");
		
		return mv;
	}
	
	@RequestMapping("/test3.do")
	public String test3(RedirectAttributes attr) {
		//���û�ϸ鼭 ������ ���������� �Ѵ�.
		attr.addFlashAttribute("message", "�ٽ� �α����� �ʿ��մϴ�.");
		attr.addFlashAttribute("message2", "��Ȯ�� ���� �Է��ϼ���.");
		return "redirect:/auth/login.do";
	}
}

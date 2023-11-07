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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.dto.CustomerVO;
import com.shinhan.dto.UserVO;
import com.shinhan.model.CustomerService;

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
		logger.info("요청주소: {}", request.getRequestURI());
		logger.info("요청방식: {}", request.getMethod());
		logger.info("요청한 사람 ip: {}", request.getRemoteAddr());
		logger.info("파라메터password: {}", request.getParameter("password"));
		logger.info("cust_id: {}", id);
		logger.info("password: {}", password);

		CustomerVO cust = custService.loginCheck(id, password);
		
		//세션에 저장하기
		session.setAttribute("loginCust", cust);
		session.setAttribute("login_id", id);
		session.setAttribute("login_phone", password);
		
		if(cust==null) {
			attr.addFlashAttribute("message","로그인 실패....... 다시 입력하기");
			return "redirect:/auth/login.do"; //재요청하기 위해 redirect: 를 붙인다. 다시 로그인
		} else {
			return "redirect:/firstzone/two"; //업무하러 간다.
		}
	}
	/*
	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public String signUp(UserVO user, @RequestParam(value="address", required=false) String address) { //UserVO는 default 생성자, setter
		//@RequestParam : request.getParameter("")
		//default가 required = true 즉, 필수이다. 파라메터가 없으면 오류이다.
		logger.info("입력된 사용자 정보: {}", user);
		logger.info("type='hidden' address: {}", address);
		return "redirect:/firstzone/two"; 
	}
	*/
	
	@RequestMapping(value="/signUp2.do", method=RequestMethod.POST)
	public String signUp2(@RequestParam Map<String, String> user, @RequestParam(value="address", required=false) String address) { //UserVO는 default 생성자, setter
		//@RequestParam : request.getParameter(""), map일때 생략불가
		//default가 required = true 즉, 필수이다. 파라메터가 없으면 오류이다.
		System.out.println(user.get("username"));
		logger.info("입력된 사용자 정보: {}", user);
		logger.info("type='hidden' address: {}", address);
		return "redirect:/firstzone/two"; 
	}

	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public String signUp(@ModelAttribute("user") UserVO user, @RequestParam(value="address", required=false) String address, Model model) { //UserVO는 default 생성자, setter
		//@RequestParam : request.getParameter("")
		//default가 required = true 즉, 필수이다. 파라메터가 없으면 오류이다.
		//@ModelAttribute Model에 저장하여 view에서 사용하도록 한다.
		logger.info("입력된 사용자 정보: {}", user);
		logger.info("type='hidden' address: {}", address);
		
		model.addAttribute("user2", user);
		model.addAttribute("message", "회원가입 성공 !!!!");

		return "auth/signupResult"; //forward하기
	}

	@RequestMapping("/test1.do")
	public ModelAndView test1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hobby","공부");
		mv.addObject("myname","갱유");
		mv.setViewName("firstzone/one");
		
		return mv;
	}
	
	@RequestMapping("/test2.do")
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView("firstzone/one");
		mv.addObject("hobby","공부2");
		mv.addObject("myname","갱유2");
		
		return mv;
	}
	
	@RequestMapping("/test3.do")
	public String test3(RedirectAttributes attr) {
		//재요청하면서 정보를 가져가고자 한다.
		attr.addFlashAttribute("message", "다시 로그인이 필요합니다.");
		attr.addFlashAttribute("message2", "정확한 값을 입력하세요.");
		return "redirect:/auth/login.do";
	}
}

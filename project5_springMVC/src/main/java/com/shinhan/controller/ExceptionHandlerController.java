package com.shinhan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//@ControllerAdvice
public class ExceptionHandlerController {

	Logger logger = LoggerFactory.getLogger("ExceptionHandlerController.class");

	@ExceptionHandler(Exception.class)
	public String errorProcess500(Exception ex) {
		logger.warn("==== 500 오류입니다. =====");
		logger.warn(ex.getClass().getSimpleName());
		logger.warn(ex.getClass().getPackageName());
		logger.warn(ex.getMessage());

		ex.printStackTrace();

		return "error/errorPage500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerError404(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "404오류");
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error/errorPage404");
		return mv;
	}
}

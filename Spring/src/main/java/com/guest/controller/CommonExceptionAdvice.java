package com.guest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.myapp.HomeController;

@ControllerAdvice//컨트롤러에서
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e){
		logger.info(e.toString());
		//234Page

		//Controller의 처리 결과를 보여줄 view와 view에서 사용할 값을 전달하는 클래스
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/error_common");//이동할 페이지 
		modelAndView.addObject("exception",e);//이동할 페이지에 넣을 값들 {키,값}
		return modelAndView;
	}
}

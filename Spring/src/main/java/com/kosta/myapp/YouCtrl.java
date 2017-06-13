package com.kosta.myapp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.web.TestVO;

@Controller
public class YouCtrl {

	@RequestMapping("/juwon")
	public String you() {
		return "hello";
	}

	@RequestMapping("/login")
	public String loginForm() {
		return "0613/login";
	}

	// 로그인 처리
	@RequestMapping("/0613/login2")
	public String login(String id, String pass) {
		// id=gildong, pass=1234 였을때 로그인 성공!!
		System.out.println("id=" + id + ", pass=" + pass);

		if (id.equals("gildong") && pass.equals("1234"))
			return "0613/success";
		else
			return "0613/fail";
	}

	@RequestMapping("/login3")
	public String loginForm3() {
		return "0613/login";
	}

	// 로그인 처리
	@RequestMapping("/0613/login3")
	public String login3(TestVO vo) {
		// id=gildong, pass=1234 였을때 로그인 성공!!
		System.out.println("id=" + vo.getId() + ", pass=" + vo.getPass());

		if (vo.getId().equals("gildong") && vo.getPass().equals("1234"))
			return "0613/success";
		else
			return "0613/fail";
	}
}

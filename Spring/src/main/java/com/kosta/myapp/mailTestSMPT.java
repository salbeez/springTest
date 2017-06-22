package com.kosta.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mailTest/*")
public class mailTestSMPT {

	@RequestMapping("/send")
	public void sendMail() {
		
	}
}

package com.github.study.ex01.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.study.ex01.vo.MemverVo;

@Controller
@RequestMapping("/ex01")
public class SampleController3 {
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);

	@RequestMapping("/doD")
	public String doD(Model model) {

		logger.info("doD 실행 .....");

		MemverVo member = new MemverVo();
		member.setUserId("userId-01");
		member.setUserPw("userPw-01");

		model.addAttribute("member", member);
		logger.info("MemberVo : " + member.toString());

		return "ex01/data";
	}

	@RequestMapping("/doE")
	public String doE(Model model) {
		logger.info("doE 실행.....");

		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", "userId-02");
		map.put("userPw", "userPw-02");
		model.addAttribute("map", map);
		
		return "ex01/data";
	}
}

package com.guest.controller;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.MemberVO;

/*@RestController
@RequestMapping("sample")*/
@Controller
@RequestMapping("/test/*")
public class SampleController {

/*	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}

	@RequestMapping("/sendMap")
	public Map<Integer, MemberVO> sendMap() {
		Map<Integer, MemberVO> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("user"+i);
			vo.setUsername("홍길동");
			vo.setUserpw("1234");
			vo.setRegdate(new Date());
			map.put(i, vo);
		}
		return map;
	}
	
	@RequestMapping("sendErrorAuth")//
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("sendErrorNot")
	public ResponseEntity<List<MemberVO>> sendListNot(){
		List<MemberVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("user"+i);
			vo.setUsername("홍길동");
			vo.setUserpw("1234");
			vo.setRegdate(new Date());
			list.add(vo);
		}
		return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
	}*/
	
	@RequestMapping(value = "/test" ,method=RequestMethod.GET)
	public void ajaxTest(){
		
	}
}
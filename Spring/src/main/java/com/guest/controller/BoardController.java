package com.guest.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guest.domain.GuestVO;
import com.guest.service.BoardService;
import com.kosta.myapp.HomeController;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(GuestVO vo, Model model) throws Exception {
		logger.info("register get.....");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(GuestVO vo, RedirectAttributes rttr) throws Exception {

		logger.info("register post.......");
		logger.info(vo.toString());

		service.regist(vo);

		rttr.addFlashAttribute("msg", "SUCCESS");
		// return "/board/success";
		return "redirect:/board/listAll";
	}

	@RequestMapping("/board/listAll")
	public void listAll(Model model) throws Exception {
		logger.info("show all list................");
		model.addAttribute("list", service.listAll());
	}

	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
		// @RequestParam은 request.getParameter과 유사하다 다른점은 문자열,숫자,날짜들의 형변환이 가능하다
	}

	@RequestMapping(value = "/board/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		service.read(bno);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {// name값 bno를
																	// 알아서 찾음
		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)//수정창이 따로 있음
	public String modifyPOST(GuestVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("mod post......");
		service.modify(vo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";

	}
}

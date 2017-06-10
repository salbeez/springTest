package com.kosta.myapp.s0610.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.myapp.HomeController;
import com.kosta.myapp.s0610.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardCtrl {
	@Resource(name = "boardService")
	private BoardService boardService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public String boardList(Model model) throws Exception {

		List list = boardService.s_ALLBoardList();

		logger.info(list.toString());

		model.addAttribute("list", list);

		return "boardList";
	}
}

package org.zerock.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.ProductVo;


@Controller
public class SampleCtrl2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleCtrl2.class);

	@RequestMapping("doC")
	public String doA(@ModelAttribute("msg") String msg) {
		logger.info("doC 실행");
		return "sample/result";
	}

	@RequestMapping("doD")
	public String doB(Model model) {
		// productVO를 만들고
		ProductVo product = new ProductVo("apple", 10000);
		logger.info("doB 실행");

		//model.addAttribute(product);
		return "sample/productDetail";
	}

	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) {

		logger.info("doE Redirect로 실행");

		rttr.addFlashAttribute("msg", "This is the msg ! with Redirect");
		return "redirect:/doF"; // doF는 @RequestMapping된 단어로 sample/doF하면 메시지 전달
								// x
	}

	@RequestMapping("doF")
	public void doF(@ModelAttribute("msg") String msg) {
		logger.info("doF 실행 msg : " + msg);
	}

	@RequestMapping("/doJSON")
	public @ResponseBody ProductVo doJSON() {
		logger.info("/doJSON 실행");
		ProductVo vo = new ProductVo("한글", 30000);
		return vo;
	}
}

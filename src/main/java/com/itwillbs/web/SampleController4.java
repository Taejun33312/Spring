package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);

	// http://localhost:8080/web/doE
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr) {

		logger.info(" doE() 실행 ");

		// => Model 객체 처럼 view 페이지에 정보를 전달, redirect 할때만 사용 가능
		// rttr.addFlashAttribute(attributeValue);
		rttr.addAttribute("msg", "itwill"); 
		// => URI에 표시 O(쿼리 스트링), F5(새로고침) 가능, 데이터 유지
		rttr.addFlashAttribute("msg2", "busan"); // 데이터를 보냈을때 한번만 사용 가능
		// => URI에 표시 X, F5(새로고침) 불가능, 데이터 유지 불가(1회성 데이터)
		// rttr.addFlashAttribute 사용 예시) 조회수 (처음 왔을때만 조회수가 올라가는 동작 등)

		// return "/doF"; //(doE를 호출해도 리턴값을 /doF를 주면 doF 페이지 호출 하게됨)
		return "redirect:/doF"; // 화면 이동 O, 주소 변경 O
		// return "forward:/doF"; // 화면 이동 O, 주소 변경 X
	}

	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String msg, @ModelAttribute("msg2") String msg2) {

		logger.info(" doF() 실행 ");
		logger.info("msg : " + msg);
		logger.info("msg2 : " + msg2);

	}
}

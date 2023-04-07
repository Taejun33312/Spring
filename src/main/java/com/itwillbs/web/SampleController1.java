package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller : 해당 클래스가 컨트롤러의 동작을 수행 하도록 선언

@Controller
//@RequestMapping("/member/*") => framework에서 공식화 된 페이지 이동 방식
//@RequestMapping("*.me")
public class SampleController1 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController1.class);
	
	// 기존의 if-else 주소 비교 하던걸 => 메서드로 동작을 구현
	// http://localhost:8080/web/doA
	
	// * 메서드의 리턴 타입이 void 일때, 매핑되는 주소 이름의 페이지(jsp)를 자동으로 연결 한다.
	// /WEB-INF/views/doA.jsp 
	// 메서드에 리턴이 void면 주소의 이름으로 jsp페이지를 호출한다.
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA() 실행!!!");
	}
	
	// doB 주소를 사용해서 페이지 연결
	// http://localhost:8080/web/doB
	@RequestMapping("/doB")
	public void doB() {
		logger.info("doB() 실행!!!");
	}
}

package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);

	// 사용불가 리턴 : int, double

	// 메서드의 리턴타입이 String일때, 리턴되는 문자열.jsp 페이지로 이동하게 된다

	// http://localhost:8080/web/doC
	// @RequestMapping(value = "/doC", method = RequestMethod.GET) 
	// doC라는 value로 get방식으로 호출 했을때 메서드를 실행 해라.
	@GetMapping(value = "/doC") // framework 4버전 부터는 GetMapping를 지원
	public String doC() {
		logger.info("doC() 호출");
		return "itwill";
	}

	// http://localhost:8080/web/doC1
	// http://localhost:8080/web/doC1?name=itwill

	// @RequestParam("name") String name (파라미터가 int라면 int로 저장할 수 있다. 즉, 캐스팅이 된다.)
	// => "name" 이름의 파라미터 정보를 name(String) 변수에 저장 하겠다.
	// request.getparamater("name") 동작과 동일한 의미 (파라미터가 String으로만 받아 옴)
	// =>> key-value 1:1 관계 (key에 해당하는 데이터만 들고 온다는 의미), Controller에서만 사용가능

	@GetMapping(value = "/doC1")
	public String doC1(@RequestParam("name") String name) {
		logger.info("doC1() 호출");
//		request.getparamater("name") => request라는 내장 객체가 없기 때문에 사용 불가
		logger.info("name : " + name);
		return "itwill";
	}

	// @ModelAttribute("name")[생략가능] String name
	// => key-value 1:N 관계 (전달되는 데이터 타입이 뭐든간에 한번에 많은양의 데이터를 가져올 수 있다.)
	// (bean/collection)
	// =>> @RequestParam동작 수행 => Model 객체에 저장 => 사용, view페이지 까지 전달 가능

	// http://localhost:8080/web/doC2?name=itwill
	// http://localhost:8080/web/doC2?userid=itwill&userpw=1234
	@GetMapping(value = "/doC2")
	// public String doC2(@ModelAttribute("name") String name) {
	// public String doC2(String name, int age) {
	public String doC2(MemberVO vo) {
		logger.info("doC2() 호출");
//		logger.info("name : " + name);
//		logger.info("age : " + age);
		logger.info(vo+"");
		// model.addAttribute("memberVo",vo); Controller에서 정보를 저장해서 view페이지로 보내는 목적 (내부적으로 생략 되어있음)
		
		return "itwill";
	}
}

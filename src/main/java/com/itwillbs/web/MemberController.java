package com.itwillbs.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	// Service 객체 필요(DAO 접근을 위해서)
	// private MemberService service = new MemberServiceImpl(); (x) // MVC에서 action과
	// 같은 역할을 한다고 보면 됨.
	@Inject // 객체를 주입 한다는것은 이미 객체가 어딘가에 만들어져 있다는 뜻 (만들어진 완제품을 제공)
	private MemberService service; // 객체 의존 주입 (DI)

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// http://localhost:8080/web/insertForm (x)
	// http://localhost:8080/web/member/insertForm
	// => 서버의 시작주소(프로젝트명) /web => / 으로 변경
	// http://localhost:8080/member/insertForm
	// Controller에 주소매핑이 생기면 호출되는 주소가 바뀐다 / 호출 되는 view 페이지의 위치도 바뀐다
	// (memberController에서 사용되는 view들만 모인다)
	// 회원가입 - 정보 입력
	@RequestMapping("/insertForm")
	public void memberInsert() {
		logger.debug(" memberInsert() 실행 ");
		logger.debug(" 주소에 해당하는 view페이지 연결 ");
	}

	// 회원가입 - 정보 처리
	@RequestMapping(value = "/insertPro")
	public void memberInsertPro(MemberVO vo) {
		logger.debug(" memberInsertPro() 실행 ");
		/*
		 * 기존 MVC 형태 한글처리 => 필터 request.setCharacterEncoding("UTF-8"); 전달정보(파라미터)
		 * 저장(회원가입정보) String userid = request.getParameter("userid");
		 * logger.info("userid : "+userid);
		 */

		// 스프링 - 메서드 전달인자를 통해서 자동으로 파라미터 수집
		logger.info("vo : " + vo);
		// DB에 저장 =>> 이제부터는 DAO가 아닌 서비스 객체를 주입
		// 기존 MVC : MemberDAO 객체 생성 => 강한 결합
		// Spring : MemberDAO 객체 주입 => 약한 결합 (DI 의존주입)
		// =>> Controller - 서비스 - DAO 직접 연결
		logger.info(service + "");
		service.memberJoin(vo);

		// 페이지 이동(login)
	}

	//////////////////////////////////////////////////////////////////////////////
	// 회원가입
	// 기존의 MVC와 차이점 똑같은 주소로 두가지 동작을 한다 method로 구분함

	// http://localhost:8080/member/insert
	// 회원가입 - 정보 입력
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertGET() {
		logger.info(" insertGET() 호출 ");
		logger.info(" /insert 주소에 연결된 view 페이지(./member/insert.jsp)로 이동 ");

		return "/member/insertForm";
	}

	// 회원가입 - 정보 처리
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) {
		logger.info(" insertPOST() 호출 ");
		// 한글 처리 (web.xml 필터 설정)
		// 전달 정보 - 파라미터 저장 (컨트롤러 파라미터 자동수집)
		logger.info(vo + "");

		// 정보 저장 -> Service -> DB(DAO)
		service.memberJoin(vo);

		// 페이지 이동(로그인 페이지)
		return "redirect:/member/login";
	}

	// 주소가 있고 방식이 매핑되어 있다면 정의되어 있는 방식을 제외한 주소를 호출하면 HTTP 상태 405
	// http://localhost:8080/member/login
	// 로그인 - 정보 입력
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.info(" loginGET() 호출 ");
		logger.info(" /login 주소에 연결된 view 페이지 (./member/login.jsp)로 이동 ");
	}

	// 로그인 - 정보 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session /* @ModelAttribute("userid") String userid */) {
		logger.info(" loginPOST() 호출 ");
		// 전달 정보 저장(파라미터)
		logger.info(vo + "");

		// Service -> DAO -> 로그인 판단
		MemberVO loginResultVO = service.memberLogin(vo);

		if (loginResultVO != null) {
			// 로그인 성공
			logger.info(" 로그인 성공 ");
			// 아이디 정보를 session에 저장
			session.setAttribute("id", loginResultVO.getUserid());
			// main 페이지 이동
			return "redirect:/member/main";
		} else {                                                                               
			// 로그인 실패
			logger.info(" 로그인 실패 ");
			// 로그인 페이지로 이동
			return "redirect:/member/login";
		}
	}
	
	// 메인페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET() {
		logger.info(" mainGET() 호출 ");
		
		logger.info(" /main -> main.jsp 페이지 이동 ");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.info(" logoutGET() 호출 ");
		
		session.invalidate();
		logger.info(" session 정보 삭제 ");
		return "redirect:/member/main";
	}
	
	// 회원 정보 조회
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String infoGET(HttpSession session, Model model) {
		logger.info(" infoGET() 호출 ");
		// 회원 아이디(PK) => session
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/member/login";
		}
		// 회원 정보 조회 => Service => DAO
		MemberVO vo= service.memberInfo(id);
		// DB에서 가져온 데이터를 view 페이지로 전달
		model.addAttribute(vo);
		logger.info(" /info -> info.jsp 페이지 이동 ");
		return "/member/info";
	}
}

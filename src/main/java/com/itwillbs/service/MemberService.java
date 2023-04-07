package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// DAO 역할과 유사함
	// 실제 사용될 서비스의 형태를 선언하는 역할
	
	// 회원가입 - memberJoin
	public void memberJoin(MemberVO vo);
	
	// 로그인 - memberLogin
	public MemberVO memberLogin(MemberVO vo);
	
	// 회원 정보 조회 - memberInfo
	public MemberVO memberInfo(String userid);
}

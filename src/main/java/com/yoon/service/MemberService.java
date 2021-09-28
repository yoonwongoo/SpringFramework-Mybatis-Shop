package com.yoon.service;



import com.yoon.model.MemberVO;


public interface MemberService {
	
	//회원가입
	public void memberJoin(MemberVO memberVo) throws Exception;

	//아이디 중복확인
	public int idCheck(String memberId) throws Exception;
	
	//로그인
	public MemberVO memberLogin(MemberVO memberVo) throws Exception;
}

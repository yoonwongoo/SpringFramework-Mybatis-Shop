package com.yoon.mapper;





import com.yoon.model.MemberVO;



public interface MemberMapper {
	
	//회원가입 
	public void memberJoin(MemberVO memberVo);

	//아이디 중복확인
	public int idCheck(String memberId);
	
	//로그인
	public MemberVO memberLogin(MemberVO memberVo);
}

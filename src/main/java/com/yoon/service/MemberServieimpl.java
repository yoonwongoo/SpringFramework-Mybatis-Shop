package com.yoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yoon.mapper.MemberMapper;
import com.yoon.model.MemberVO;



@Service
public class MemberServieimpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	//회원가입 메서드
	@Override
	public void memberJoin(MemberVO memberVo) throws Exception {
		
		String raw = memberVo.getMemberPw();
		String enc = encoder.encode(raw);
		
		memberVo.setMemberPw(enc);
		memberMapper.memberJoin(memberVo);
		
	}
	//아이디 중복체크
	@Override
	public int idCheck(String memberId) throws Exception {
	
		return memberMapper.idCheck(memberId) ;
	}
	
	//로그인
	@Override
	public MemberVO memberLogin(MemberVO memberVo) throws Exception {
		
	
		
		MemberVO mv = memberMapper.memberLogin(memberVo);
		
		if(mv != null ) {
			
			if(true == encoder.matches(memberVo.getMemberPw(),mv.getMemberPw())){
				
				mv.setMemberPw("");//비밀번호을 초기화 하여 넘긴다.
				return mv;
				
			} else {
				MemberVO mv1 = new MemberVO();
				mv1 = null;
				return mv1;
			}
			
		}else{
			
			MemberVO mv1 = new MemberVO();
			mv1 = null;
			return mv1;
			
			}
			
		
	}

}

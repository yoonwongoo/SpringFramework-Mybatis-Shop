package com.yoon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yoon.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor{
	
	@Autowired
	HttpSession session;
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		session = request.getSession();
		
		MemberVO memberVo = (MemberVO) session.getAttribute("member");//로그인시 "member라고 세션저장함."
		
		if(memberVo == null || memberVo.getAdminCk() == 0){
			
			response.sendRedirect("/");
			
			return false;
			
		}
		
		
		
		return true;
	}



	
	

}

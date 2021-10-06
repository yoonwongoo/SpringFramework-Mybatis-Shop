package com.yoon.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoon.model.MemberVO;
import com.yoon.service.MemberService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	
	HttpSession session;


	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@GetMapping("/join")
	public void joinForm() {

		logger.info("회원가입 화면 진입");
	}

	@GetMapping("/login")
	public void loginForm() {

		logger.info("로그인 화면 진입");
	}

	// 회원가입
	@PostMapping("/join")
	public String joinPost(MemberVO memberVo) throws Exception {
		
		memberService.memberJoin(memberVo);

		logger.info("회원가입 완료");

		return "redirect:/";
	}

	// 아이디 중복체크
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {

		int result = memberService.idCheck(memberId);

		if (result != 0) {

			return "fail";

		} else {

			return "success";
		}

	}

	
	/*
	 * @RequestBody / 정리. 클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로
	 * 보내면, 서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.
	 * 
	 * @ResponseBody
	 * 
	 * 서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로
	 * 변환하여 클라이언트로 전송한다.
	 */


	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		// 난수생성 즉, 인증번호생성.
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		//
		String setFrom = "yunyun26@naver.com";
		String toMail = email;
		String title = "회원가입 인증번호입니다";
		String content = "홈페이지를 방문해주셔서 감사합니다" + "<br><br>" + "인증번호는 " + checkNum + "입니다" + "<br>"
				+ "회원가입 홈페이지로 돌아가 인증번호를 입력해주세요";

		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			javaMailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);// 데이터를 반환 해줄 때 스트링타입으로만 반환을 해준다.

		return num;
	}

	// 로그인
	@PostMapping("/login")
	public String loginPost(MemberVO memberVo, RedirectAttributes rttr, HttpServletRequest request) throws Exception {

		int result = 0;
		session = request.getSession();
		
		logger.info("로그인post");
		
		MemberVO member = memberService.memberLogin(memberVo);
		
		if(member != null) {
			 member.setMemberPw("");
			session.setAttribute("member", member);
			
			return "redirect:/";
		}
			
		else {
			rttr.addAttribute("result", result);
			return "redirect:/member/login";	
		}
		
		
		 
		
	}
	
	
	@PostMapping("/logout")
	@ResponseBody
	public void logoutGet(HttpServletRequest request) throws Exception {
		
		logger.info("로그아웃");
		
		session = request.getSession();
		
		session.invalidate();
		
	
	}
	
	
	}


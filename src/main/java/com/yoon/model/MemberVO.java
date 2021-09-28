package com.yoon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {
	private String memberId;
	
	private String memberPw;
	
	private String memberName;
	
	private String memberMail;
	
	private String memberAddr1;
	
	private String memberAddr2;
	
	private String memberAddr3;
	
	private int adminCk;
	
	private int regDate;
	
	private int money;
	
	private int point;

}

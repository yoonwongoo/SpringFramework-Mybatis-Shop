<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="resources/css/main.css">
</head>
<body>

	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">
		<c:choose>
			<c:when test="${member==null}">
				<ul class = "list">
					<li>
						<a href ="/member/login">로그인</a>
					</li>
			
					<li>
						<a href ="/member/join">회원가입</a>
					</li>
					<li>
						고객센터
					</li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class = "list">
					<c:if test="${member.adminCk == 1}">
						<li>
							<a href ="/admin/main">관리자페이지</a>
						</li>	
					</c:if>
					<li>
						<a href ="/member/logout">로그아웃</a>
					</li>
			
					<li>
						장바구니
					</li>
					<li>
						나의페이지
					</li>
				</ul>
			</c:otherwise>
		</c:choose>
			
			</div>
			<div class="top_area">
				<div class="logo_area">
					<h1>logo area</h1>
				</div>
				<div class="search_area">
					<h1>Search area</h1>
				</div>
				<c:if test="${member == null }">
					<div class="login_area">
						<div class="login_button">
							<a href="/member/login">로그인</a>
						</div>
						<span><a href="/member/join">회원가입</a></span>
						</div>
				</c:if>
				<!-- 로그인한 상태 -->
				<c:if test="${member != null }">
					<div class="login_success_area">
						<span>회원 : ${member.memberName}</span> 
						<span>충전금액 : <fmt:formatNumber value="${member.money}" pattern="#,###.##"/></span>
						 <span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
						 <a href="/member/logout">로그아웃</a>
					</div>
				</c:if>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="navi_bar_area">
			<h1>navi area</h1>
		</div>
		<div class="content_area">
			<h1>content area</h1>
		</div>
	</div>


</body>
</html>
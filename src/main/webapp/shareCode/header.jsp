<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="functions"
	uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShareCode</title>


<link rel="stylesheet" href="css/writing.css" type="text/css">
<link rel="stylesheet" href="css/header.css" type="text/css">
<link rel="stylesheet" href="css/login.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.2.0/css/all.min.css"
	integrity="sha512-6c4nX2tn5KbzeBJo9Ywpa0Gkt+mzCzJBrE1RB6fmpcsoN+b/w/euwIMuQKNyUoU/nToKN3a8SgNOtPrbW12fug=="
	crossorigin="anonymous" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header class="header">

		<a href="/web/shareCode/list.do" class="logo">ShareCode</a> <input class="menu-btn"
			type="checkbox" id="menu-btn" /> <label class="menu-icon"
			for="menu-btn"><span class="navicon"></span></label>


		<div class="wrap">
			<div class="search">
				<form name="form1" method="post" action="board.do">
					<select name="searchOption" id="post_category"
						style="margin-right: 2%; width: 25%">
						<option value="all"
							<c:out value="${map.searchOption == 'all' ? 'selected' : '' }" />>전체</option>
						<option value="Java"
							<c:out value="${map.searchOption == 'Java' ? 'selected' : '' }" />>Java</option>
						<option value="Python"
							<c:out value="${map.searchOption == 'Python' ? 'selected' : '' }" />>Python</option>
						<option value="C%2B%2B"
							<c:out value="${map.searchOption == 'C%2B%2B' ? 'selected' : '' }" />>C++</option>
						<option value="Other"
							<c:out value="${map.searchOption == 'Other' ? 'selected' : '' }" />>기타</option>
					</select> <input type="text" name="keyword" value="${map.keyword}"
						class="searchTerm" placeholder="검색어를 입력해주세요" style="width: 55%">
					<button type="submit" class="searchButton">
						<i class="fa fa-search"></i>
					</button>
				</form>
			</div>
		</div>


		<c:if test="${member == null &&kakaoid == null}">
			<div>
				<ul class="menu">
					<li><a href="/web/shareCode/signup.jsp">SignUp</a></li>
					<li><a href="#about" id="popup_open_btn">Login</a></li>
				</ul>
			</div>
		</c:if>

		<c:if test="${member != null}">
			<div class="login_menu">
				<p class="helle_user">
					[ ${member.user_id} ] 님 환영합니다 :)
					<button id="logoutBtn" type="button">logout</button>
				</p>

			</div>
		</c:if>

		<c:if test="${kakaoid != null}">
			<div class="login_menu">
				<p class="helle_user">
					[ ${kakaoid} ] 님 환영합니다 :) <a
						href="https://kauth.kakao.com/oauth/logout?
						client_id=2790956db8b98f04db2d8d8df073fa48&
						logout_redirect_uri=http://localhost:8080/web/shareCode/logout.do"><button
							id="logoutBtn" type="button">logout</button></a>
				</p>

			</div>
		</c:if>

		<input type="hidden" value="${member.user_id}" id="loginUser">
	</header>




	<%@include file="login.jsp"%>
	<script src="js/header.js"></script>
</body>
</html>
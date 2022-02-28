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
<link rel="stylesheet" href="css/login.css" type="text/css">
</head>


<body>

	<script src="js/login.js"></script>
	<div id="my_login_modal">
		<div id="my_modal">
			<div>
				<h1 class="login_title">LOGIN</h1>
				<a class="modal_close_btn">ⅹ</a>
				<form name="homeForm" method="post" id="homeForm">
					<c:if test="${member == null &&kakaoid == null}">
						<div>
							<span class="login_font">ID <input type="text"
								name="user_id" id="user_id" class="user_id_pass"
								placeholder="아이디를 입력해주세요"> <br>
							</span> <br> <span class="login_font">PASS</span> <input
								type="password" class="user_id_pass" name="user_pw" id="user_pw"
								placeholder="비밀번호를 입력해주세요">
							<button type="button" class="login_btn" id="btnLogin" >login</button>
							<hr
								style="border: 1px color= silver; margin: 15% 0% 15% 0%; width: 100%;">
							<a
								href="https://kauth.kakao.com/oauth/authorize?
								client_id=2790956db8b98f04db2d8d8df073fa48&
								redirect_uri=http://localhost:8080/web/shareCode/home.do&
								response_type=code"><img src="img/kakao_login_medium_wide.png"
								style="margin-bottom:  15%;" /></a>
						</div>
					</c:if>
				</form>
			</div>

		</div>
	</div>

</body>
</html>
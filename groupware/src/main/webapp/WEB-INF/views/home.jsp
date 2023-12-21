<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
	<title>GRUOUP WARE LOGIN</title>
</head>
<script type="text/javascript" src='https://code.jquery.com/jquery-3.4.1.js'></script>
<style>
	.groupware-title { font-size: 36px; }
	.groupware-section-login { text-align: center; width: 600px; margin: 0 auto; margin-top: 150px; }
	.groupware-login-thumb { width: 200px; }
	.groupware-login-field { margin: 0 auto; width: 400px; }
	.groupware-login-field input, button { width: 100%; padding: 10px; margin: 10px 0px; }
</style>
<body>
	<form action='/login.do' method='POST'>
		<section class="groupware-section-login">
			<h1 class="groupware-title">GROUP WARE</h1>
			<div>
				<img class="groupware-login-thumb" src="/resources/images/employee.png" alt=""/>
			</div>
			<div class="groupware-login-field">
				<input class="user_id" type='text' name='user_id' placeholder="아이디를 입력하세요.">
				<input class="user_pswd" type='password' name='user_pswd' placeholder="비밀번호를 입력하세요.">
				<button class="groupware-login-btn" type="submit">로그인</button>
			</div>
		</section>
	</form>
</body>
</html>
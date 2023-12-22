<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
	<title>GRUOUP WARE LOGIN</title>
</head>
<script type="text/javascript" src='https://code.jquery.com/jquery-3.4.1.js'></script>
<script type="text/javascript">
	// window.onload: DOM이 전부 다 그려진 후 호출 
	window.onload = function() {
		const loginBtn = document.querySelector('.groupware-login-btn');
		
		loginBtn.addEventListener('click', () => {
			const userInfo = {
				'user_id': document.querySelector('.user_id').value,
				'user_pswd': document.querySelector('.user_pswd').value
			}
			
			
			getUsers(userInfo);
		});
	}
	
	function getUsers(userInfo) {
		console.log('::: 호출 :::');
		
		$.ajax({
			type: 'POST'
			, url: '/login.do'
			, data: JSON.stringify(userInfo)
			, dataType: 'json'
			, contentType: 'application/json'
			, success: function(event) {
				console.log(event);
				if (event.result === 'fail') {
					alert('존재하지 않는 회원입니다.');
					location.reload();
				} else {
					location.href = "returnMain.do"
					 }
			}
		});
	};
</script>
<style>
	.groupware-title { font-size: 36px; }
	.groupware-section-login { text-align: center; width: 600px; margin: 0 auto; margin-top: 150px; }
	.groupware-login-thumb { width: 200px; }
	.groupware-login-field { margin: 0 auto; width: 400px; }
	.groupware-login-field input { width: 100%; padding: 10px; margin: 10px 0px; }
	.groupware-login-field button { display: inline-block; 	width: 50%; padding: 10px; margin: 10px 0px; }
	
</style>
<body>
	<section class="groupware-section-login">
		<h1 class="groupware-title">GROUP WARE</h1>
		<div>
			<img class="groupware-login-thumb" src="/resources/images/employee.png" alt=""/>
		</div>
		<div class="groupware-login-field">
			<input class="user_id" type='text' name='user_id' placeholder="아이디를 입력하세요.">
			<input class="user_pswd" type='password' name='user_pswd' placeholder="비밀번호를 입력하세요.">
			<button class="groupware-login-btn" type="button">로그인</button>
			<button class="groupware-signup-btn" type="button" onClick="location.href='returnSignUp.do'">회원가입</button>
		</div>
	</section>
</body>
</html>
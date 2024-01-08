<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
<title>SIGN UP</title>

</head>
<body>
	<h1 class="groupware-title">SIGNUP</h1>

	<form id="registerForm">
		아이디 : <input type="text" id="user_id" required><br> 
		비밀번호 : <input type="password" id="user_pswd" required><br> 
		이름 : <input type="text" id="user_name" required><br>
		번호 : <input type="text" id="user_num" required><br>
		<button class="groupware-signup-btn" type="button">회원가입</button>
	</form>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
        window.onload = function() {
        	const signUpBtn = document.querySelector('.groupware-signup-btn');
        	
        	signUpBtn.addEventListener('click', () => {
        		const signUpInfo = {
        			user_id : $('#user_id').val(),
        			user_pswd : $('#user_pswd').val(),
        			user_name : $('#user_name').val(),
        			user_num : $('#user_num').val()
        		}
        		
       		signUp(signUpInfo);
       		});
        	
        	
        	function signUp(signUpInfo) {
        		console.log('::: signup 호출 :::');
        		
        		$.ajax({
        			type: 'POST',
        			url: '/signup.do',
        			data: JSON.stringify(signUpInfo),
        			dataType: 'json',
        			contentType: 'application/json',
        			success: function(userKey) {
        				if (userKey == 1) {
        					alert('가입 완료');
        					location.href = "returnMain.do";
        				} else {
        					alert(' 실패  ');
        					window.location.reload();
        				}
        			},
        			 error: function(error) {
        				alert(' 실패  ');
       					window.location.reload();
       				}
        		});
        	}
        }
    </script>

</body>
</html>
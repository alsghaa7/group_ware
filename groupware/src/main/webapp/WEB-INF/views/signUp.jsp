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
		이메일 : <input type="text" id="user_email" required><br>
		전화번호 : <input type="text" id="user_tel" required><br>
		주소 : <input type="text" id="user_address1" required><br>
		상세주소 : <input type="text" id="user_address2" required><br>
		성별<br> <input type="checkbox" id="user_gender" value='m' onClick="clickCheck(this)" required>남성
			<input type="checkbox" id="user_gender" value='f' onClick="clickCheck(this)" required>여성
		
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
        			user_email : $('#user_email').val(),
        			user_tel : $('#user_tel').val(),
        			user_address1 : $('#user_address1').val(),
        			user_address2 : $('#user_address2').val(),
        			user_gender : $('#user_gender').val()
        		}
        		
       		signUp(signUpInfo);
       		});
        	
        	
        	function signUp(signUpInfo) {
        		console.log('::: signup 호출 :::');
        		
        		$.ajax({
        			type: 'POST',
        			url: '/sign.do',
        			data: JSON.stringify(signUpInfo),
        			dataType: 'json',
        			contentType: 'application/json',
        			success: function(userKey) {
        				if (userKey == 1) {
        					alert('가입 완료');
        					location.href = "/";
        				} else {
        					alert(' 실패  ');
        					window.location.reload();
        				}
        			},
        			 error: function(error) {
        				alert(' 완전실패  ');
       					window.location.reload();
       				}
        		});
        	}
        }
        
        function clickCheck(target) {
        	document.querySelectorAll('input[type=checkbox]')
        		.forEach(el => el.checked = false);
        		
        	target.checked = true;
        }
    </script>

</body>
</html>
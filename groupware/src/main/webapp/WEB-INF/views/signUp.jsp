<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
<title>SIGN UP</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
        $(document).ready(function () {
            $("#registerForm").submit(function (event) {
                // 폼 제출을 중지
                event.preventDefault();

                // 사용자로부터 입력 받은 값 추출
                var user_id = $("#user_id").val();
                var user_pswd = $("#user_pswd").val();
                var user_name = $("#user_name").val();
                var user_num = $("#user_num").val();

                // Ajax를 사용하여 서버로 값 전송
                $.ajax({
                    type: "POST",
                    url: "/signup.do",
                    data: {
                        user_id: user_id,
                        user_pswd: user_pswd,
                        user_name: user_name,
                        user_num: user_num
                    },
                    success: function (data) {
                        // 서버로부터의 응답 처리 (예: 성공 메시지 표시)
                        var temp = data;
                        if(temp!=0){
                        alert("회원가입이 완료되었습니다!");
                        location.href="returnMain.do";
                        } else {
                        alert("error");
                    },
                    error: function (error) {
                        location.href="returnMain.do";
                        alert("오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</head>
<body>
	<h1 class="groupware-title">SIGNUP</h1>


	<form id="registerForm">
		아이디 : <input type="text" id="user_id" required><br> 
		비밀번호 : <input type="password" id="user_pswd" required><br> 
		이름 : <input type="text" id="user_name" required><br>
		번호 : <input type="text" id="user_num" required><br>
		<input type="submit" value="가입하기">
	</form>

</body>

</html>
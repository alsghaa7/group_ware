<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
<title>PROD INSERT</title>

</head>
<body>
	<h1 class="groupware-title">SIGNUP</h1>

	<form id="registerForm">
	 
		상품명 : <input type="text" id="prod_name" required><br> 
		종류 : <input type="text" id="prod_category" required><br>
		가격 : <input type="text" id="prod_price" required><br>
		수량 : <input type="text" id="prod_amount" required><br>
		
		<button class="prodInsert-btn" type="button"> 등 록 </button>
	</form>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
        window.onload = function() {
        	const insertBtn = document.querySelector('.prodInsert-btn');
        	
        	insertBtn.addEventListener('click', () => {
        		const prodInfo = {
        		
        			prod_name : $('#prod_name').val(),
        			prod_category : $('#prod_category').val(),
        			prod_price : $('#prod_price').val(),
        			prod_amount : $('#prod_amount').val()
        		}
        		
       			prodInsert(prodInfo);
       		});
        	
        	
        	function prodInsert(prodInfo) {
        		console.log('::: prodInfo 호출 :::');
        		
        		$.ajax({
        			type: 'POST',
        			url: '/prodInsert.do',
        			data: JSON.stringify(prodInfo),
        			dataType: 'json',
        			contentType: 'application/json',
        			success: function(data) {
        				if (data == 1) {
        					alert('등록 완료');
        					location.href = "/returnMain.do";
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
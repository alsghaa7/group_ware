<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
	<title>DETAIL</title>
</head>
<body>
	<h1>PRODUCT DETAIL</h1>
	
	<form id="registerForm">

		상품명 : <input type="text" id="prod_name" value="${dto.prod_name}"><br> 
		종류 : <input type="text" id="prod_category" value="${dto.prod_category}"><br>
		가격 : <input type="text" id="prod_price" value="${dto.prod_price}"><br>

<!-- 		<button class="prodUpdate" type="button"> 수 정 </button>
			<button class="prodDelete" type="button"> 삭 제 </button> -->
		<button class="basket" type="button"> 장바구니 </button>
	</form>
	
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
window.onload = function() {
      	const Update = document.querySelector('.prodUpdate');
      	const Delete = document.querySelector('.prodDelete');
      	
      	Delete.addEventListener('click', () => {
      		const deleteInfo = {
      			'prod_no' : ${dto.prod_no}
      		}
      		prodDelete(deleteInfo);
      	});
      	
     	Update.addEventListener('click', () => {
     		const updateInfo = {
     		
     			'prod_name' : $('#prod_name').val(),
     			'prod_category' : $('#prod_category').val(),
     			'prod_price' : $('#prod_price').val(),
     			'prod_amount' : $('#prod_amount').val(),
     			'prod_no':${dto.prod_no}
     		}
    		prodUpdate(updateInfo);
    	});
      	
      	function prodDelete(deleteInfo) {
      		console.log('::: delete 호출 :::');
      		
      		$.ajax({
      			type: 'POST',
      			url: '/deleteProd.do',
      			data: JSON.stringify(deleteInfo),
      			dataType: 'json',
      			contentType: 'application/json',
      			success: function(data) {
      				if (data == 1) {
      					alert('삭제 완료');
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
      	function prodUpdate(updateInfo) {
      		console.log('::: updateInfo 호출 :::');
      		
      		$.ajax({
      			type: 'POST',
      			url: '/updateProd.do',
      			data: JSON.stringify(updateInfo),
      			dataType: 'json',
      			contentType: 'application/json',
      			success: function(data) {
      				if (data == 1) {
      					alert('수정 완료');
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
</script>
</body>
</html>
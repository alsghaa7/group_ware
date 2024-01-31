<%@ page language='java' contentType='text/html; charset=utf-8' pageEncoding='utf-8' %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta charset='UTF-8'>
	<title>MAIN</title>
</head>
<body>

	<h1 class="main-title">@ MAIN @</h1>
	<input id="keyword" type="text" value="" placeholder="검색어">
	<button onClick="getSearchList();">검샥</button>
	<hr>
	<ul id="catList">
	
	</ul>
	<hr><br>
	<ul id="prodList">
		
	</ul>
	
	<button><a href="/returnProdInsert.do">상품 등록</a></button>
	
	
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
// window.onload: DOM이 전부 다 그려진 후 호출 
	window.onload = function() {
		getProducts();
		getCatList();
	}
	
	function getProducts() {
		$.ajax({
			type: 'GET'
			, url: '/prodList.do'
			, dataType: 'json'
			, contentType: 'application/json'
			, success: function(data) {
				console.log(data);
				const list = data.list;
				let html = "";
				
				list.forEach(raw => {
					html += "<button onClick='locationurl("+"\""+raw.prod_no+"\""+");'><li>이름: "+ raw.prod_name +" / 가격: "+ raw.prod_price +"</li></button><br>";
				});
				document.getElementById('prodList').innerHTML = html;
			}
		});
	};
	
	function getCatList() {
		$.ajax({
			type: 'GET',
			url: '/categoryList.do',
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				console.log(data);
				let html = "";
				html += "<button onClick='getProducts();'><li>전체</li></button><br>";
				data.forEach(raw => {
					html += "<button onClick='getSortedList("+"\""+raw.prod_category+"\""+");'><li>"+ raw.prod_category + "</li></button><br>";
				});
				document.getElementById('catList').innerHTML = html;
			}
		});
	}
	
	function getSortedList(cate) {
		let body = {
			'prod_category' : cate
		}
		$.ajax({
			type: 'POST',
			url: '/sortByCategory.do',
			data: JSON.stringify(body),
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				console.log(data);
				
				let html = "";
				data.sort.forEach(raw => {
					html += "<button onClick='locationurl("+"\""+raw.prod_no+"\""+");'><li>이름: "+ raw.prod_name +" / 가격: "+ raw.prod_price +"</li></button><br>";
				});
				document.getElementById('prodList').innerHTML = html;
			}
		});
	}
	
	function getSearchList() {
		let body = {
			'keyword': document.getElementById('keyword').value
		}
		$.ajax({
			type: 'POST'
			, url: '/searchList.do'
			, data: JSON.stringify(body)
			, dataType: 'json'
			, contentType: 'application/json'
			, success: function(data) {
				console.log(data.list);
				
				let html = "";
				data.list.forEach(raw => {
					html += "<button onClick='locationurl("+"\""+raw.prod_no+"\""+");'><li>이름: "+ raw.prod_name +" / 가격: "+ raw.prod_price +"</li></button><br>";
				});
				document.getElementById('prodList').innerHTML = html;
			}
		});
	}
	
	function locationurl(no) {
		location.href="/returnDetail.do?prod_no="+no;
	}
</script>
</body>

</head>

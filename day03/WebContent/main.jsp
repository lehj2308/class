<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ㅁㅁ쇼핑몰 페이지</title>
</head>
<body>


<%=session.getAttribute("username")%>님 환영합니다! <br>
<h2>=== 상품 목록 ===</h2>
<form action="addProduct.jsp" name="form1" method="post">
	<select name="product">
		<option>셔츠</option>
		<option>블라우스</option>
		<option selected>반팔티</option>
		<option>청바지</option>
		<option>운동화</option>
	</select>
	<input type="number" value="1" name="cnt">
	<input type="submit" value="상품 추가하기">
</form>
<hr>
<a href="buy.jsp">구매하기</a>


</body>
</html>
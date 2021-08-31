<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 페이지</title>
</head>
<body>

	<% request.setCharacterEncoding("UTF-8"); %>

	<h1>사용자 페이지</h1>
	<hr>
	<h2><%=request.getParameter("userName")%>님 환영합니다.
	</h2>
	<hr>
	<form action="add.jsp" name="addForm" method="post">
		<table>
			<tr>
				<th>상품명</th>
				<td><select name="product">
						<option selected>티셔츠</option>
						<option>바지</option>
						<option>신발</option>
				</select></td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="number" name="cnt" value="1"></td>
			</tr>
			<tr>
				<th cospan="2"><input type="submit" name="add" value="장바구니 추가"></th>
			</tr>
		</table>
	</form>
		<button type="button" onclick="location.href='buy.jsp' ">결제하기</button>



</body>
</html>
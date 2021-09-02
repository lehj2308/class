<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>${userID}님 환영합니다.</h1>
<hr>

	<form method="post" action="D.jsp">
		<mytag:product name="data" />

		<input type="number" name="cnt" value="1"> <input
			type="submit" value="구매하기">
	</form>
<hr>
<a href="control.jsp?action=logout">로그아웃</a>
</body>
</html>
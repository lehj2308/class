<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style type="text/css">
tr {
	width: 50%;
}
</style>
</head>
<body>

	<%
		session.invalidate();
	%>

	<h1>일반 사용자 로그인</h1>


	<form action="check.jsp" method="post" name="checkForm">
		<input type="text" name="userName"> <input type="submit"
			value="로그인">
	</form>

</body>
</html>
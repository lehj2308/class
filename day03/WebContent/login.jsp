<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

<%
	session.invalidate(); // 세션 정보 해제
%>

<h2>어서오세요, ㅁㅁ쇼핑몰입니다!</h2>
<form action="NewFile.jsp" method="post" name="form1">
	<input type="text" name="username">
	<input type="submit" value="로그인하기">
</form>

</body>
</html>
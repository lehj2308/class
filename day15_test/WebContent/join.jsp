<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="join.do" method="post" name="join">
<table border="1">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" required></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="unum" required></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" required></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="회원가입"></td>
	</tr>
</table>
</form>

</body>
</html>
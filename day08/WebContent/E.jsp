<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>로그인 창</h3>
<hr>

<form action="control.jsp" method="post" name="form1">
	<input type="hidden" name= "action" value="login">
	<table class="alt">
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userID" required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userPW" required></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th colspan='2'><input type="submit" value="로그인">
			</tr>
		</tfoot>
	</table>
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="day04.SignupVO" %>
    <jsp:useBean id="signupDAO" class="day04.SignupDAO" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="login.html">처음으로</a> <br>
<hr>
<table border="1">
	<tr>
	<th>이름</th><th>아이디</th><th>비밀번호</th>
	</tr>
	<% 
		for(SignupVO vo:signupDAO.getMembers()){
	%>
	<tr>
		<td><%=vo.getUserName()%></td>
		<td><%=vo.getUserID()%></td>
		<td><%=vo.getUserPW()%></td>
	</tr>
	<%
		}
	%>
</table>

</body>
</html>
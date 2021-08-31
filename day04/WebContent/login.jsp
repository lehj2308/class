<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="lb" class="day04.LoginBean" />
<jsp:setProperty property="*" name="lb" />
<%
	if (lb.check()) {
		out.println("<h1>로그인 성공</h1>");
	}
	else{
		out.println("<h1>로그인 실패</h1>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

입력한 아이디: <jsp:getProperty property="userID" name="lb"/>
입력한 패스워드: <jsp:getProperty property="userPW" name="lb"/>








</body>
</html>
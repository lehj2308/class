<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 내장객체</title>
</head>
<body>

<%
   application.setAttribute("username", "홍길동");
   application.setAttribute("cnt", 1);
%>

<a href="hong.jsp">홍길동님의 미니홈피</a>

</body>
</html>
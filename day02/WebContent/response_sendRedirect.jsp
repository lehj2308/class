<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리다이렉팅 방식</title>
</head>
<body>

	<% response.sendRedirect("result.jsp"); %>

</body>
</html>
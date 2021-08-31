<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add_On</title>
</head>
<body>

	<h1>저희 Add_On을 찾아주셔서 감사합니다.</h1>
	<hr>
	<%
		if (session.isNew()) {
			out.println("로그인을 해주세요. <br><a href='login.jsp'>로그인</a>");
		} else {
			String user= request.getParameter("user");
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(3);
			out.println(user + "님 환영합니다.<br>");
		}
	%>
	<hr>

	<%
		int cnt = (Integer) application.getAttribute("cnt");
		cnt++;
		application.setAttribute("cnt", cnt);
	%>

	<h3>
		방문자 수:<%=cnt%></h3>

</body>
</html>
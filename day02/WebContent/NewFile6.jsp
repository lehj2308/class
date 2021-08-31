<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체-request 결과 페이지</title>
</head>
<body>

	이름:
	<%=request.getParameter("username")%>
	<br> 학년:
	<%=request.getParameter("grade")%>
	<br> 수강하는과목
	<br>
	<%
		String data[] = request.getParameterValues("subject");
		if (data == null) {
			out.println("수강과목 없음");
		} else{
			for (String v : data) {
				out.println(v + "<br>");
			}
		}
	%>
</body>
</html>
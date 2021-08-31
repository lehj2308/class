<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 확인창</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		String userName = request.getParameter("userName");
		if (userName == "") {

		out.println("<script>alert('유저이름을 입력해주세요.');history.go(-1);</script>");

		}
		else if(userName.equals("관리자")){
			response.sendRedirect("admin.jsp");
		}

		else{
	%>
<jsp:forward page='user.jsp' />

<%} %>
</body>
</html>
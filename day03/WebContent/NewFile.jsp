<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String username=request.getParameter("username");
	if(username.equals("")){
		out.println("<script>alert('이름을 입력하세요!');history.go(-1);</script>");
	}
	else{
		session.setAttribute("username", username);
		response.sendRedirect("main.jsp");
	}
%>
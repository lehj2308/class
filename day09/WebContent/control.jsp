<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<jsp:setProperty property="*" name="memberVO" />

<%
	String action=request.getParameter("action");
	
	if(action.equals("login")){
		if(memberDAO.login(memberVO)){
			pageContext.forward("main.jsp");
		}
		else{
			out.println("<script>alert('로그인 정보를 확인하세요!');history.go(-1)</script>");
		}
	}
	else if(action.equals("logout")){
		session.invalidate();
		response.sendRedirect("main.jsp");
	}

	%>
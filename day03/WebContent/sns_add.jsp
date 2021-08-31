<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 등록 페이지</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	String msg=request.getParameter("msg");
	String username=(String)session.getAttribute("username");
	ArrayList<String> msgs=(ArrayList<String>)application.getAttribute("msgs");
	if(msgs==null){
		// 최초등록
		msgs=new ArrayList<String>();
		application.setAttribute("msgs", msgs);
	}
	msgs.add(username+"님이 ["+msg+"] 등록");
	
	response.sendRedirect("sns_login.jsp");
%>

</body>
</html>
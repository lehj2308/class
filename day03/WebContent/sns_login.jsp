<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 완료된 화면</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	String username=request.getParameter("username");
	
	if(username!=null){
		session.setAttribute("username", username);
	}
%>

<h1>현재 페이지</h1>
<hr>
<form action="sns_add.jsp" method="post">
	[<%=session.getAttribute("username") %>] <input type="text" name="msg"> <input type="submit" value="댓글 작성하기">
</form>
<hr>
<ol>
<%
	ArrayList<String> msgs=(ArrayList<String>)application.getAttribute("msgs");
	if(msgs!=null){
		for(String v:msgs){
			out.println("<li>"+v+"</li>");
		}
	}
%>
</ol>





</body>
</html>
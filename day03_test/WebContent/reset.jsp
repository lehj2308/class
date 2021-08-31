<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int pay=1;
	if(application.getAttribute("pay")!=null){
	pay=(Integer)application.getAttribute("pay")+1;
	}
	application.setAttribute("pay", pay);
	response.sendRedirect("index.jsp");
%>

</body>
</html>
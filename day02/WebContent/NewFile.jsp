<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2><%= new Date() %></h2>
 <!-- JSP 표현식=> 서블릿파일로 변환되었을때, -->
 <%-- NewFile_jsp.java안에서 표현식 부분은 out.println(new Date()); --%>

</body>
</html>
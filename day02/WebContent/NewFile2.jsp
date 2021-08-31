<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 지시어</title>
</head>
<body>

	<%@ include file="menu.jsp"%>
	<hr>
	<div id="box1"><%@ include file="news.jsp"%></div>
	<div id="box1"><%@ include file="shopping.jsp"%></div>

</body>
</html>
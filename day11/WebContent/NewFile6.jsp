<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:url value="NewFile2.jsp" var="testURL">
	<c:param name="test">b</c:param>
</c:url>

<h2>${testURL}</h2>
<a href="${testURL}">눌러보세요!</a>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul></ul>
<c:forTokens var="v" items="홍길동,아무무,아리,티모,가렌" delims=",">
	<li>${v}</li>
</c:forTokens>
</ul>

<hr>

<c:forEach var="v" items="${members}" begin="0" end="3" varStatus="vs">
	${vs.index} / ${vs.count}<br>
	${v.name} / ${v.email} <hr>
</c:forEach>

</body>
</html>
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

<c:set var="msg" value="user" />
<c:if test="${msg=='test'}" var="res">
	1-결과는 [${res }]입니다.
</c:if>
<c:if test="${msg!='test'}" var="res">
	2-결과는 [${res }]입니다.
</c:if>

</body>
</html>
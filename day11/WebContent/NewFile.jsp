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

<!-- isErrorPage -->
<c:catch var="errMsg">
	<%=10/0 %>
</c:catch>
${errMsg}<br>

<c:set var="msg" value="안녕하세요!" />

<c:remove var="msg"/>
<h1>${msg}</h1>
<hr>
<c:set target="${member}" property="email" value="dlvgusvwns@naver.com"/>
${member.name }<br>
${member.email  }

</body>
</html>
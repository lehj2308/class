<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="login.do" method="post">
	<table border="1">
		<tr>
			<td bgcolor="pink"><%-- <spring:message code="message.login.id" /> --%></td>
			<td><input type="text" name="id" value="${guest.id}"></td>
		</tr>
		<tr>
			<td bgcolor="pink"><%-- <spring:message code="message.login.password" /> --%></td>
			<td><input type="password" name="password" value="${guest.password}"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" <%-- value="<spring:message code="message.login.login" />" --%>></td>
		</tr>
	</table>
</form>

</body>
</html>
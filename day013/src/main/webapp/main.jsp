<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.tBoard.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${user.name}<spring:message code="message.main.hello" /></h2>
<h3><a href="logout.do"><spring:message code="message.main.logout" /></a></h3>
<hr>
<h1><spring:message code="message.main.boardlist" /></h1>

<form action="search.do" method="post">
	<table>
		<tr>
			<th><select name="condition">
					<c:forEach var="op" items="${sm}">
						<option value="${op.value}">${op.key}</option>
					</c:forEach>
				</select>
			</th>
			<td><input type="text" name="keyword"></td>
			<td><input type="submit" value="<spring:message code="message.main.search" />"></td>
		</tr>
	</table>
</form>
<table border="1">
	<tr>
		<td><spring:message code="message.main.listnum" /></td>
		<td><spring:message code="message.main.listtitle" /></td>
		<td><spring:message code="message.main.listwriter" /></td>
	</tr>
	<c:forEach var="v" items="${tBoardList}">
	<tr>
		<td>${v.id}</td>
		<td><a href="getTBoard.do?id=${v.id}">${v.title}</a></td>
		<td>${v.writer}</td>
	</tr>
	</c:forEach>
</table>
<a href="main.do?page=${page-1}">이전 페이지</a>
<a href="main.do?page=${page+1}">다음 페이지</a>

<hr>
<a href="insertTBoard.jsp"><spring:message code="message.main.insert" /></a>
</body>
</html>
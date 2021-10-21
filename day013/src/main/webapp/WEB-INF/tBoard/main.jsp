<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.tBoard.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${user.name}님</h2>
<h3><a href="logout.do">로그아웃</a></h3>
<hr>
<h1>글 목록</h1>
<table border="1">
	<tr>
		<td>글 번호</td>
		<td>글 제목</td>
		<td>글 작성자</td>
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
<a href="insertTBoard.jsp">글 작성</a>
</body>
</html>
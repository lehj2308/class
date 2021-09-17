<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>

<!-- 공지글 -->
	<hr>
	<h1>공지</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성날짜</th>
		</tr>
		<c:forEach var="v" items="${공지 글 리스트}">
			<tr>
				<td>${v.bId}</td>
				<td><a href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.bTitle}</a></td>
				<td>${v.bDate}</td>
			</tr>
		</c:forEach>
	</table>
<!-- 공지글  END-->
<hr>
<!-- 정렬 버튼 -->
<table border="1">
	<tr>
		<th><button type="button" onclick="location.href='control.jsp?action=최신순'">최신순</button></th>
		<th><button type="button" onclick="location.href='control.jsp?action=조회순'">조회순</button></th>
		<th><button type="button" onclick="location.href='control.jsp?action=댓글순'">댓글순</button></th>
	</tr>
</table>
<!-- 정렬 버튼 END-->

<hr>
<!-- 검색창 및 글쓰기 버튼-->
	<form method="post" action="control.jsp" name="bSearch">
		<input type="hidden" name="action" value="b검색">
		<table border="1">
			<tr>
				<td><input class="" type="text" name="title" required>
					<span class=""><button class="" type="submit">검색</button></span></td>
					<!-- 로그인시에만 -->
				<td><button type="button" class=""
						onclick="location.href='form.jsp'">글 쓰기</button></td>
			</tr>
		</table>
	</form>
<!-- 검색창 및 글쓰기 버튼 END-->
<hr>
<!-- 게시판 글-->
<hr>
	<h1>게시판</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>테마</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="v" items="${게시판 글 리스트}">
			<tr>
				<td>${v.bId}</td>
				<td>${v.bLang}</td>
				<td><a href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.bTitle}</a></td>
				<td>${v.bWriter}</td>
				<td>${v.bDate}</td>
				<td>${v.bHit}</td>
			</tr>
		</c:forEach>
	</table>
<!-- 게시판 글 END-->
</body>
</html>
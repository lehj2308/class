<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코딩 테스트</title>
</head>
<body>

	<!-- 정렬 버튼 -->
	<table border="1">
		<tr>
			<th><button type="button"
					onclick="location.href='control.jsp?action=최신순'">최신순</button></th>
			<th><button type="button"
					onclick="location.href='control.jsp?action=조회순'">조회순</button></th>
			<th><button type="button"
					onclick="location.href='control.jsp?action=댓글순'">댓글순</button></th>
		</tr>
	</table>
	<!-- 정렬 버튼 END-->

	<hr>
	<!-- 검색창 및 글쓰기 버튼-->
	<form method="post" action="control.jsp" name="tSearch">
		<input type="hidden" name="action" value="t검색">
		<table border="1">
			<tr>
				<td><input class="" type="text" name="tTitle" required>
					<span class=""><button class="" type="submit">검색</button></span></td>
				<!-- 로그인시에만 -->
				<td><button type="button" class=""
						onclick="location.href='formTest.jsp'">글 쓰기</button></td>
			</tr>
		</table>
	</form>
	<!-- 검색창 및 글쓰기 버튼 END-->
	<hr>
	<!-- 코딩 테스트 글-->
	<hr>
	<h1>코딩 테스트</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>테마</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="v" items="${코딩 테스트 글 리스트}">
			<tr>
				<td>${v.tId}</td>
				<td>${v.tLang}</td>
				<td><a href="control.jsp?action='코딩테스트 글보기'&bId=${v.tId}">${v.tTitle}</a></td>
				<td>${v.tWriter}</td>
				<td>${v.tDate}</td>
				<td>${v.tHit}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 게시판 글 END-->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
</head>
<body>

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
		<input type="hidden" name="bCtgr" value="Q&A">
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
	
		<c:forEach var="v" items="${게시판 글 리스트}">
		<table>
			<tr>
				<td>${v.bLang}</td>
				<td><a href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.bTitle}</a></td>
				<td>${v.bWriter}</td>
				<td>${v.bDate}</td>
			</tr>
			<tr>
				<td colspan="3"><textarea name="bContent" rows="6"
						style="resize: none;" readonly>${v.bContent}</textarea></td>
				<td>${v.bHit}</td>
			</tr>
			</table>
		</c:forEach>
	</table>
<!-- 게시판 글 END-->
</body>
</html>
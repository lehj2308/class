<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<!-- 내정보 -->
	<table border="1">
		<tr>
			<th colspan="2">${세션.iconId}${세션.userId}</th>
		</tr>
		<tr>
			<th>휴대폰 번호</th>
			<td>${세션.userHp}></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${세션.userEmail}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${세션.userGender}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${세션.userBirth}</td>
		</tr>
		<tr>
			<th colspan='2'><button type="button"
					onclick="location.href='control.jsp?action=프로필수정'">프로필 수정</button></th>
		</tr>
	</table>
	<!-- 내정보 END -->

	<!-- 내가쓴 게시글 -->
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성날짜</th>
		</tr>
		<c:forEach var="v" items="${내가쓴 글 리스트}">
			<tr>
				<td>${v.bId}</td>
				<td><a href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.bTitle}</a></td>
				<td>${v.bDate}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 내가쓴 게시글 END-->


</body>
</html>
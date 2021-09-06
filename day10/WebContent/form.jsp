<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="insertDB"> <input
			type="hidden" name="writer" value="${memberVO.userID}">
		<table border ="1">
			<tr>
				<th>작성자</th>
				<td>${memberVO.userID}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="6"></textarea></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="글 작성하기"></th>
			</tr>
		</table>
	</form>
	<hr>
	<a href="control.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>
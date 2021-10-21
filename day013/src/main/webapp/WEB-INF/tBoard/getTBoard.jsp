<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="updateTBoard.do" method="post">
		<table border="1">
			<input type="hidden" value="${tBoard.id}" name="id" />
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${tBoard.title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" value="${tBoard.content}"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 변경하기"></td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="deleteTBoard.do?id=${tBoard.id}">글 삭제하기</a>
	<hr>
	<a href="main.do">메인페이지로 이동하기</a>

</body>
</html>
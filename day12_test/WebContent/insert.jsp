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
		<input type="hidden" name="action" value="insert">
		<table border ="1">
			<tr>
				<th>제품명</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" min="0" name="price" value="3000"></td>
			</tr>
			<tr>
				<th>소개</th>
				<td><textarea name="information" rows="6"></textarea></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="제품 등록"></th>
			</tr>
		</table>
	</form>
	<hr>
	<a href="control.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function del() {
		result = confirm("정말로 삭제하시겠습니까?");
		if (result == true) {
			document.form1.action.value = "delete";
			document.form1.submit();
		} else {
			return;
		}
	}
</script>
</head>
<body>

	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="num" value="${data.num}">
		<table border="1">
			<tr>
				<th>제품명</th>
				<td><input type="text" name="name" value="${data.name}"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" min="0" name="price" value="${data.price}"></td>
			</tr>
			<tr>
				<th>소개</th>
				<td><textarea name="information" rows="6">${data.information}</textarea></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="제품 수정하기"> <input
					type="button" value="제품 삭제하기" onClick="del()"></th>
			</tr>
		</table>
	</form>

	<a href="control.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>
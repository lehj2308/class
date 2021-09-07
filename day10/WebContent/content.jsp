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
			document.form1.action.value = "deleteDB";
			document.form1.submit();
		} else {
			return;
		}
	}
</script>
</head>
<body>

	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="updateDB"> <input
			type="hidden" name="mnum" value="${data.mnum}">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"
					value="${data.writer}" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${data.title}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="6">${data.content}</textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><input type="date" value="${data.wdate}" readonly></td>
			</tr>
			<tr>
				<th colspan='2'>
				
				<c:if test="${data.writer == mem.userID }">
					<input type="submit" value="글 수정하기"> <input
					type="button" value="글 삭제하기" onClick="del()">
				</c:if>
					
			</tr>
		</table>
	</form>
	
	<a href="control.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>
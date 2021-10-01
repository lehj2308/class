<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<!-- 작성 페이지 -->
	<%-- <c:if test="작성 페이지라면"> --%>
	<form method="post" action="control.jsp" name="insert">
		<input type="hidden" name="action" value="코딩글insert"> <input
			type="hidden" name="userNum" value="${세션값.userNum}">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="tTitle" required></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="tWriter" value="${세션값.userId}"
					readonly></td>
			</tr>
			<tr>
				<th>태그</th>
				<td><input type="text" name="tLang" required></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tContent" rows="6"
						style="resize: none;" required></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tEx" rows="6"
						style="resize: none;" required></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tAnswer" rows="6"
						style="resize: none;" required></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 작성"></td>
			</tr>
		</table>
	</form>
	<%-- </c:if> --%>
	<!-- 작성 페이지 END-->

	<!-- 수정 페이지 -->
	<%-- <c:if test="수정 페이지라면"> --%>

	<form method="post" action="control.jsp" name="edit">
		<input type="hidden" name="action" value="코딩글update">
		<!-- userNum없는상태 ?? -->
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="tTitle" value="${param.tTitle}" required></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="tWriter" value="${param.tWriter}"
					readonly></td>
			</tr>
			<tr>
				<th>태그</th>
				<td><input type="text" name="tLang" value="${param.tLang}" required></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tContent" rows="6"
						style="resize: none;" required>${param.tContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tEx" rows="6"
						style="resize: none;" required>${param.tEx}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="tAnswer" rows="6"
						style="resize: none;" required>${param.tAnswer}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 수정"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="글 삭제" onclick="del()"></td>
			</tr>
		</table>
	</form>

	<%-- </c:if> --%>
	<!-- 수정 페이지 END-->
</body>
<script type="text/javascript">
	function del() {
		result = confirm("글을 삭제하시겠습니까?");
		if (result == true) {
			document.edit.action.value = "글삭제";
			document.edit.submit();
		} else {
			return;
		}
	}
</script>
</html>
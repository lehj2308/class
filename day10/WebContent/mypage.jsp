<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>${mem.userID}님마이페이지입니다.</h1>
	<a href="control.jsp?action=main">메인으로 돌아가기</a>
	<hr>
	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="userUpdate">
		<table border="1">
			<tr>
				<th>새 비밀번호</th>
				<th><input type="text" name="newPW" required></th>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="비밀번호 수정"></th>
			</tr>
			<tr>
				<th colspan="2"><input type="button" value="회원탈퇴" onclick="del()">
				</th>
			</tr>
		</table>
	</form>
	<hr>
	<a href="control.jsp?action=main">메인으로 돌아가기</a>

	<script type="text/javascript">
	function del() {
		result = confirm("탈퇴하시겠습니까?");
		if (result == true) {
			document.form1.action.value = "userDelete";
			document.form1.submit();
		} else {
			return;
		}
	}
</script>
</body>

</html>


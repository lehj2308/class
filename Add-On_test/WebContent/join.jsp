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

	<!-- 회원가입 -->
	<%-- <c:if test="회원가입 페이지라면"> --%>
	<form action="control.jsp" method="post" name="join">
		<input type="hidden" name="action" value="회원가입">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" required></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId" required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userPw" required></td>
			</tr>
			<tr>
				<th colspan="2">아이콘</th>
			</tr>
			<tr>
				<td><input type="radio" name="iconId" value="?" required>1번
					<input type="radio" name="iconId" value="?">2번 <input
					type="radio" name="iconId" value="?">3번</td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="userHp" onkeypress="inNumber();"
					maxlength="11" required /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="userEmail" required /></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>?</td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="userGender" value="woman"
					required>여성 <input type="radio" name="userGender"
					value="man">남성</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="userBirth" required /></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="회원가입"></th>
			</tr>
		</table>
	</form>
	<%-- </c:if> --%>
	<!-- 회원가입 END-->
	
	<!-- 프로필수정 -->
	<%-- <c:if test="프로필수정 페이지라면"> --%>
	<form action="control.jsp" method="post" name="edit">
		<input type="hidden" name="action" value="프로필Update">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" value="${세션.userName}" required></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="userPw" value="${세션.userPw}" required></td>
			</tr>
			<tr>
				<th colspan="2">아이콘</th>
			</tr>
			<tr>
				<td><input type="radio" name="iconId" value="?" required>1번
					<input type="radio" name="iconId" value="?">2번 <input
					type="radio" name="iconId" value="?">3번</td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="userHp" onkeypress="inNumber();"
					maxlength="11" value="${세션.userHp}" required /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="userEmail" value="${세션.userEmail}" required /></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>?</td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="userGender" value="woman"
					required>여성 <input type="radio" name="userGender"
					value="man">남성</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="userBirth" value="${세션.userBirth}" required /></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="프로필 수정"></th>
			</tr>
			
			<tr>
				<th colspan='2'><input type="button" value="회원탈퇴" onclick="del()"></th>
			</tr>
		</table>
	</form>
	<%-- </c:if> --%>
	<!-- 프로필수정 END-->

</body>
<script type="text/javascript">
	function inNumber() {
		if (event.keyCode<48 || event.keyCode>57) {
			event.returnValue = false;
		}
	}
	
	function del() {
		result = confirm("탈퇴하시겠습니까?");
		if (result == true) {
			document.edit.action.value = "회원탈퇴";
			document.edit.submit();
		} else {
			return;
		}
	}
</script>
</html>
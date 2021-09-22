<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${user.unum == null}">
	<form action="login.do" method="post" name="login">
<input type="hidden" name="mcnt" value="${mcnt}">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="unum" required></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인"></td>
			</tr>
		</table>
	</form>

	<input type="button" value="회원가입" onclick="showPopup();" />

</c:if>

<c:if test="${user.unum != null}">
${user.unum}님 환영합니다.
<a href="main.do?selUser=${user.unum}">내글목록보기</a>
	<a href="logout.do">로그아웃</a>
</c:if>

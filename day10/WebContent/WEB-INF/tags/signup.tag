<%@ tag language="java" pageEncoding="UTF-8"%>

<form action="control.jsp" method="post" name="form1">
	<input type="hidden" name="action" value="signup">
	<table class="alt">
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userID" required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userPW" required></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th colspan='2'><input type="submit" value="회원가입"></th>
			</tr>
		</tfoot>
	</table>
</form>
<hr>
<a href="control.jsp?action=main">메인으로 돌아가기</a>
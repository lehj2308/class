<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="control.jsp" name="form1">
	<input type="hidden" name="action" value="aTrans">
		<table border="1">
			<tr>
				<td>${aBankVO.aname}</td>
				<td>${aBankVO.abalance}</td>
			</tr>
			<tr>
				<td><input type="number" name="bal" min="1" required></td>
				<td><input type="submit" value="계좌이체"></td>
			</tr>
		</table>
	</form>

	<form method="post" action="control.jsp" name="form2">
	<input type="hidden" name="action" value="bTrans">
		<table border="1">
			<tr>
				<td>${bBankVO.bname}</td>
				<td>${bBankVO.bbalance}</td>
			</tr>
			<tr>
				<td><input type="number" name="bal" min="1" required></td>
				<td><input type="submit" value="계좌이체"></td>
			</tr>
		</table>
	</form>

</body>
</html>
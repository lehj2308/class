<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body>

<form method="post" action="formTest.jsp" name="detailTest">
		
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="tTitle" value="${test.tTitle}" readonly></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="tWriter" value="${test.tWriter}" readonly></td>
			</tr>
			<tr>
				<th>분야</th>
				<td><input type="text" name="tLang" value="${test.tLang}" readonly></td>
			</tr>
			<tr><td colspan="2">문제</td></tr>
			<tr>
				<td colspan="2"><textarea name="tContent" rows="6"
						style="resize: none;" readonly>${test.tContent}</textarea></td>
			</tr>
			<tr><td colspan="2">출력예시</td></tr>
			<tr>
				<td colspan="2"><textarea name="tEx" rows="6"
						style="resize: none;" readonly>${test.tEx}</textarea></td>
			</tr>
			<tr><td colspan="2">정답</td></tr>
			<tr>
				<td colspan="2"><textarea name="tAnswer" rows="6"
						style="resize: none;" readonly>${test.tAnswer}</textarea></td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>${test.tDate}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 수정"></td>
			</tr>
		</table>
	</form>
	
	
	
	

</body>
</html>
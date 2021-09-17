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

<form method="post" action="form.jsp" name="detail">
		
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="bTitle" value="${param.bTitle}" readonly></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="bWriter" value="${param.bWriter}" readonly></td>
			</tr>
			<tr>
				<th>게시판 종류</th>
				<td><input type="text" name="bCtgr" value="${param.bCtgr}" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="bTitle" value="${param.bTitle}" readonly></td>
			</tr>
			<tr>
				<th>분야</th>
				<td><input type="text" name="bLang" value="${param.bLang}" readonly></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="bContent" rows="6"
						style="resize: none;" readonly>${param.bContent}</textarea></td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>${param.bDate}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글 수정"></td>
			</tr>
		</table>
	</form>
	
	
	
	

</body>
</html>
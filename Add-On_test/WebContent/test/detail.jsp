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
			<input type="hidden" name="bId" value="${board.bId}">
		<table border="1">
				
			<tr>
				<th>제목</th>
				<td><input type="text" name="bTitle" value="${board.bTitle}" readonly></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="bWriter" value="${board.bWriter}" readonly></td>
			</tr>
			<tr>
				<th>게시판 종류</th>
				<td><input type="text" name="bCtgr" value="${board.bCtgr}" readonly></td>
			</tr>
			<tr>
				<th>테마</th>
				<td><input type="text" name="bLang" value="${board.bLang}" readonly></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="bContent" rows="6"
						style="resize: none;" readonly>${board.bContent}</textarea></td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>${board.bDate}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="update"></td>
			</tr>
		</table>
	</form>
	
		<c:forEach var="replySet" items="${replySets}">
			<c:set var="reply" value="${replySet.rvo}"/>
			댓글번호 : ${reply.rId} 작성자 : ${reply.rWriter} 내용 : ${reply.rContent} parentId : ${reply.parentId}
			<br>
			<c:forEach var="rreply" items="${replySet.rrlist}">
			댓글번호 : ${rreply.rId} 작성자 : ${rreply.rWriter} 내용 : ${rreply.rContent} parentId : ${rreply.parentId}	
			<br>
			</c:forEach>
		
			<br>
		
		</c:forEach>
		
	
	
	

</body>
</html>
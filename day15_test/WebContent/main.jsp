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

<ol>
	<li><a href="control.jsp?action=main">전체목록보기</a></li>
</ol>

<hr>

<h2>전체목록</h2>
<c:forEach var="v" items="${datas}">
	<c:set var="m" value="${v.m}"/>
	<h3>[${m.unum}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글 ${m.replycount} | ${m.mdate}]</h3>
	<ol><c:forEach  var="r" items="${v.rlist}">
		<li>${r.unum} >> ${r.rmsg} [${r.rdate}]</li>
	</c:forEach></ol>
	<form action="control.jsp" method="post" name="${m.mnum}">
	<input type="hidden" name="action" value="reply">
	<input type="hidden" name="mnum" value="${m.mnum}">
	<input type="hidden" name="unum" value="${m.unum}">
	<input type="text" name="rmsg" required>
	<input type="submit" value="입력">
	</form>
</c:forEach>

</body>
</html>
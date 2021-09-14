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
	
	<c:if test="${user.unum != null}">
	<h3>댓글</h3>
	<form action="control.jsp" method="post" name="${m.mnum}">
	<input type="hidden" name="action" value="reply">
	<input type="hidden" name="mnum" value="${m.mnum}">
	<input type="hidden" name="unum" value="${user.unum}">
	<input type="hidden" name="mcnt" value="${mcnt}">
	<input type="text" name="rmsg" required>
	<input type="submit" value="입력">
	</form>
	</c:if>
	<hr>
</c:forEach>
<a href="control.jsp?action=main&mcnt=${mcnt+1}&selUser=${param.selUser}">더보기</a>

<c:if test="${user.unum != null}">
	<h3>게시글</h3>
	<form action="control.jsp" method="post" name="msg">
	<input type="hidden" name="action" value="message">
	<input type="hidden" name="unum" value="${user.unum}">
	<input type="hidden" name="mcnt" value="${mcnt}">
	<input type="text" name="msg" required>
	<input type="submit" value="입력">
	</form>
</c:if>

<hr>

<c:if test="${user.unum == null}">
<form action="control.jsp" method="post" name="login">
<input type="hidden" name="action" value="login">
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

<form action="control.jsp" method="post" name="join">
<input type="hidden" name="action" value="join">
<input type="hidden" name="mcnt" value="${mcnt}">
<table border="1">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" required></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="unum" required></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" required></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="회원가입"></td>
	</tr>
</table>
</form>
</c:if>

<c:if test="${user.unum != null}">
${user.unum}님 환영합니다.
<a href="control.jsp?action=main&selUser=${user.unum}">내글목록보기</a>
<a href="control.jsp?action=logout&mcnt=${mcnt}">로그아웃</a>
</c:if>
</body>
</html>
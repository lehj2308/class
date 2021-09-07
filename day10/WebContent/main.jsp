<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check() {
		var checkPW = prompt("비밀번호를 입력하세요");
		document.form2.checkPW.value = checkPW;
		document.form2.action.value = "check";
		document.form2.submit();
	}
</script>
</head>
<body>

	<c:if test="${mem.userID!=null}">
		<h1>${mem.userID}님환영합니다.</h1>
		<form method="post" action="control.jsp" name="form2">
			<input type="hidden" name="action" value="logout"> <input
				type="hidden" name="checkPW"> <input type="button"
				value="마이페이지" onclick="check()"> <input type="submit"
				value="로그아웃">
		</form>
	</c:if>
	<c:if test="${mem.userID==null}">
		<mytag:login />
	</c:if>
	<hr>
	<form method="post" action="control.jsp" name="form1">
		<input type="hidden" name="action" value="searchList">
		<table border="1">
			<tr>
				<th>글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>시간</th>
			</tr>
			<c:forEach var="v" items="${datas}">
				<tr>
					<td><a href="control.jsp?action=content&mnum=${v.mnum}">${v.mnum}</a></td>
					<td>${v.title}</td>
					<td>${v.writer}</td>
					<td>${v.wdate}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2"><input type="text" name="search" required>
					<input type="submit" value="작성자 검색"></td>

				<c:if test="${mem.userID!=null}">
					<td colspan="2" style="text-align: right"><a href="form.jsp">글
							쓰기</a><br>
					<a href="control.jsp?action=myList">내글 보기</a></td>
				</c:if>

			</tr>
		</table>
	</form>
	<hr>
	<a href="control.jsp?action=main">전체 글보기</a>
</body>
</html>
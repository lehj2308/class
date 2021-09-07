<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		ArrayList<String> datas = new ArrayList();
		datas.add("모자");
		datas.add("티셔츠");
		datas.add("악세사리");
		datas.add("바지");
		datas.add("신발");

		pageContext.setAttribute("datas", datas);
	%>
	<table border="1">
		<tr>
			<c:forEach var="v" items="${datas}">
				<td>${v}</td>
			</c:forEach>
		</tr>
	</table>

	<form>
		<input type="number" name="num" value="1"> <input
			type="submit" value="입력">
	</form>

	${param.num}

	<c:if test="${param.num % 2 == 0}">
	짝수
</c:if>
	<c:if test="${param.num % 2 == 1}">
	홀수
</c:if>



</body>
</html>
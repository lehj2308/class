<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>제품 번호</th>
			<th>제품명</th>
			<th>가격</th>
		</tr>
		<c:forEach var="v" items="${datas}">
			<tr>
				<td><a href="control.jsp?action=edit&num=${v.num}">${v.num}</a></td>
				<td>${v.name}</td>
				<td>${v.price}원</td>
			</tr>
		</c:forEach>
		<tr>
		<td colspan="3" style="text-align: right"><a href="insert.jsp"> 커피 등록 </a></td>
		</tr>
	</table>
	
	

</body>
</html>
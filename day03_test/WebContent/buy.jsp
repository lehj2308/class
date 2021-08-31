<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 창</title>
</head>
<body>

	<h3>총결제 금액</h3>
	<hr>
	<%
		ArrayList<ArrayList> lists = (ArrayList)session.getAttribute("lists");
	
		if (lists == null) {
			out.println("구매한 상품이 없습니다.");
		} else {
			for(ArrayList v:lists){
				out.println(v+"<br>");
			}
			
		
	%>
	<button onclick="location.href='reset.jsp'">결제하기</button>
	<%} %>
	<button onclick="history.go(-1)">돌아가기</button>


</body>
</html>
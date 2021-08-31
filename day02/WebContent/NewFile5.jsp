<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습</title>
<style type="text/css">
.even{
	color:blue;
}
.odd{
	color:red;
}
</style>
</head>
<body>

	<h1>1~100 숫자</h1>
	<hr>
	<%
		for (int i = 1; i <= 100; i++) {
			if(i%2==0){
				out.println("<div class=even><h1>"+i+"</h1></div>");
			}
			else{
				out.println("<div class=odd><h1>"+i+"</h1></div>");
			}
		}
	%>


</body>
</html>
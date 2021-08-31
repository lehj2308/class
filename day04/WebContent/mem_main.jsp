<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="day04.MemberVO" %>
    <jsp:useBean id="memberDAO" class="day04.MemberDAO" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="mem_reg.html">☞회원가입하러가기</a> <br>
<hr>
<table border="1">
	<tr>
	<th>이름</th><th>아이디</th><th>비밀번호</th>
	</tr>
	<% 
		for(MemberVO vo:memberDAO.getDatas()){
	%>
	<tr>
		<td><%=vo.getName()%></td>
		<td><%=vo.getUserID()%></td>
		<td><%=vo.getUserPW()%></td>
	</tr>
	<%
		}
	%>
</table>

</body>
</html>
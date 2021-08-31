<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberVO" class="day04.MemberVO" />
<jsp:setProperty property="*" name="memberVO" />
<jsp:useBean id="memberDAO" class="day04.MemberDAO" scope="application" />
<%
	memberDAO.addMember(memberVO);
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
			<td>이름</td>
			<td><%=memberVO.getName()%></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=memberVO.getUserID()%></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=memberVO.getUserPW()%></td>
		</tr>
	</table>
	
<a href="mem_main.jsp">처음으로</a>
	

</body>
</html>
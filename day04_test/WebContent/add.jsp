<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="signupVO" class="day04.SignupVO" />
<jsp:setProperty property="*" name="signupVO" />
<jsp:useBean id="signupDAO" class="day04.SignupDAO" scope="application" />
<%
	signupDAO.addMembers(signupVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
	alert("<%=signupVO.getUserName()%>님 회원가입이 완료 되었습니다.");
</script>
<% response.sendRedirect("login.html");%>

</body>
</html>
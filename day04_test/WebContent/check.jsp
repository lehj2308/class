<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="day04.SignupVO"%>
<jsp:useBean id="signupDAO" class="day04.SignupDAO" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String userID = (String) request.getParameter("userID");
		String userPW = (String) request.getParameter("userPW");
		Boolean flag = true;
		for (SignupVO vo : signupDAO.getMembers()) {
			if (vo.getUserID().equals(userID)) {
				flag = false;
				if (vo.getUserPW().equals(userPW)) {
	%>
	<jsp:forward page="memberList.jsp" />
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
	<%
		break;}
			}
		}
		if (flag) {
	%>

	<script type="text/javascript">
		alert("없는 아이디입니다.");
		history.go(-1);
	</script>
	<%
		}
	%>

</body>
</html>
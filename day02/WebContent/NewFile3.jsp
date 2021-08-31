<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp 액션 태그</title>
</head>
<body>


<h2>NewFile3.jsp 페이지입니다.</h2>
<hr>
<jsp:include page="footer.jsp">
	<jsp:param value="dlvgusvwns@naver.com" name="email" />
	<jsp:param value="010-1234-5678" name="tel" />
</jsp:include>
<!--  태그 바디 -->

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 ${param.data}을 ${param.cnt}개 구매하셔서, 총 ${productBean.calc(param.data,param.cnt)}원입니다.

</body>
</html>
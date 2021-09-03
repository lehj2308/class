<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>&lt;c:forEach&gt;실습</h1>
<hr>

<table border="1">
   <c:forEach var="v" items="${members}">
   <tr>
      <td>${v.name}</td>
      <td><c:out value="${v.email}" escapeXml="false"><font color="red">email 정보없음</font></c:out></td>
   </tr>
   </c:forEach>
</table>




</body>
</html>
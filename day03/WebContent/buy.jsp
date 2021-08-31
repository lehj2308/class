<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 페이지</title>
</head>
<body>

<h3>구매한 상품 목록</h3>
<hr>
<%
   ArrayList<String> list=(ArrayList)session.getAttribute("list");
   if(list==null){
      out.println("구매한 상품이 없습니다.");
   }
   else{
      for(String v:list){
         out.println(v+"<br>");
      }
   }
%>

</body>
</html>
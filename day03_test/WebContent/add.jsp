<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 창</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	String product = request.getParameter("product");
	int cnt = Integer.parseInt(request.getParameter("cnt"));
	int total = 0;
	
	if(product.equals("티셔츠")){
		total=cnt*5000;
	}
	else if(product.equals("바지")){
		total=cnt*7000;
	}
	else if(product.equals("신발")){
		total=cnt*10000;
	}
	
	ArrayList list = new ArrayList();
	list.add(product);
	list.add(cnt);
	
	ArrayList<ArrayList> lists = (ArrayList)session.getAttribute("lists");
	lists.add(list);
	
	session.setAttribute("lists", lists);
	
	
	if(session.getAttribute("total")==null){
		session.setAttribute("total", total);
	}
	else{
	total += (Integer)session.getAttribute("total");
	session.setAttribute("total", total);
	}
%>

<script type="text/javascript">
	alert("<%=product%>(이)가 <%=cnt%>개 추가되었습니다.");
	history.go(-1);
</script>

</body>
</html>
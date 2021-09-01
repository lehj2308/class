<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.message.*,java.util.*"%>
<jsp:useBean id="datas" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<!DOCTYPE HTML>
<!--
	Hyperspace by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Generic - Hyperspace by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a href="index.html" class="title">Hyperspace</a>
		<nav>
			<ul>
				<li><%=memberVO.getUserID()%></li>
				<li><a href="control.jsp?action=logout">로그아웃</a></li>
			</ul>
		</nav>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<h3>LIST</h3>
				<div class="table-wrapper">
					<table class="alt">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>시간</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (MessageVO vo : (ArrayList<MessageVO>) datas) {
							%>
							<tr>
								<td><a
									href="control.jsp?action=edit&mnum=<%=vo.getMnum()%>"><%=vo.getMnum()%></a></td>
								<td><%=vo.getTitle()%></td>
								<td><%=vo.getWriter()%></td>
								<td><%=vo.getWdate()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<a href="form.jsp" class="button">글 작성</a>
					<%
					String action=(String)request.getParameter("action");
					if(action.equals("myList")){
					%>
					<a href="control.jsp?action=list" class="button">전체목록</a>
					<%} else{%>
					<a href="control.jsp?action=myList" class="button">내가 쓴글</a>
					<%} %>
				</div>
			</div>
		</section>

	</div>

	<!-- Footer -->
	<footer id="footer" class="wrapper alt">
		<div class="inner">
			<ul class="menu">
				<li>&copy; Untitled. All rights reserved.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="data" class="model.message.MessageVO" scope="request" />
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

<script type="text/javascript">
	function del() {
		result = confirm("정말로 삭제하시겠습니까?");
		if (result == true) {
			document.form1.action.value = "delete";
			document.form1.submit();
		} else {
			return;
		}
	}
</script>

</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a href="index.html" class="title">Hyperspace</a>
	</header>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main" class="wrapper">
			<div class="inner">
				<a href="control.jsp?action=list">메인으로 돌아가기</a>
				<div class="table-wrapper">
					<table class="alt">
						<tbody>
							<form action="control.jsp" method="post" name="form1">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="mnum" value="<%=data.getMnum()%>">
								<table>
									<tr>
										<th>작성자</th>
										<td><input type="text" name="writer"
											value="<%=data.getWriter() %>"></td>
									</tr>
									<tr>
										<th>제목</th>
										<td><input type="text" name="title"
											value="<%=data.getTitle() %>"></td>
									</tr>
									<tr>
										<th>내용</th>
										<td><textarea name="content" id="demo-message" rows="6"
												><%=data.getContent() %></textarea></td>
									</tr>
									<tr>
										<th>작성일</th>
										<td color="black"><input type="date" name="date"
											value="<%=data.getWdate() %>"></td>
									</tr>
									<tr>
										<th colspan='2'><input type="submit" value="글 수정하기">
											<input type="button" value="글 삭제하기" onClick="del()"></th>
									</tr>
								</table>
							</form>
						</tbody>
					</table>
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
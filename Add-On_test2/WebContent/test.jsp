<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<title>Icons | Klorofil - Free Bootstrap Dashboard Template</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.jpg">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.jpg">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.jsp"><img src="assets/img/logo.png"
					alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth">
						<i class="lnr lnr-arrow-left-circle"></i>
					</button>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><img src="assets/img/user.png"
								class="img-circle" alt="Avatar"> <span>Samuel</span> <i
								class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="myPage.jsp"><i class="lnr lnr-user"></i> <span>My
											Profile</span></a></li>
								<li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="index.jsp" class=""><i class="lnr lnr-home"></i>
								<span>?????? ?????????</span></a></li>
						<li><a href="question.jsp" class=""><i
								class="lnr lnr-code"></i> <span>Q & A</span></a></li>
						<li><a href="board.jsp" class=""><i class="lnr lnr-code"></i>
								<span>?????? ?????????</span></a></li>
						<li><a href="test.jsp" class="active"><i
								class="lnr lnr-code"></i> <span>?????? ?????????</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">?????? ?????????</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<button type="button"
										onclick="location.href='control.jsp?action=?????????'"
										class="btn btn-primary btn-block">?????????</button>
								</div>
								<div class="col-md-4">
									<button type="button"
										onclick="location.href='control.jsp?action=?????????'"
										class="btn btn-primary btn-block">?????????</button>
								</div>
								<div class="col-md-4">
									<button type="button"
										onclick="location.href='control.jsp?action=?????????'"
										class="btn btn-primary btn-block">?????????</button>
								</div>
							</div>
							<br> <br>
							<div class="row">
								<div class="col-md-2">
									<form method="post" action="control.jsp" name="test">
										<input type="hidden" name="action" value="??????">
										<div class="input-group">
											<span class="input-group-btn"><button
													class="btn btn-default" type="submit">??????</button></span> <input
												class="form-control" type="text">
										</div>
									</form>
								</div>
								<div class="col-md-9"></div>
								<div class="col-md-1">
									<button type="button" onclick="location.href='detailTest.jsp'"
										class="btn btn-default btn-block">?????????</button>
								</div>
							</div>
							<br>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>??????</th>
										<th>??????</th>
										<th>??????</th>
										<th>?????????</th>
										<th>????????????</th>
										<th>?????????</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>JAVA</td>
										<td><a href="detailTest.jsp">?????????</a></td>
										<td>?????????</td>
										<td>2021-09-23</td>
										<td>10</td>
									</tr>
									<%-- 
									<c:forEach var="v" items="${?????? ????????? ??? ?????????}">
										<tr>
											<td>${v.tId}</td>
											<td>${v.tLang}</td>
											<td><a href="control.jsp?action='??????????????? ?????????'&tId=${v.tId}">${v.tTitle}</a></td>
											<td>${v.tWriter}</td>
											<td>${v.tDate}</td>
											<td>${v.tHit}</td>
										</tr>
									</c:forEach>
									 --%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<footer>
		<div class="container-fluid">
			<p class="copyright">
				&copy; 2017 <a href="https://www.themeineed.com" target="_blank">Theme
					I Need</a>. All Rights Reserved.
			</p>
		</div>
	</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>

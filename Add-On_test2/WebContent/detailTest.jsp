<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">

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
								<span>메인 페이지</span></a></li>
						<li><a href="question.jsp" class=""><i
								class="lnr lnr-code"></i> <span>Q & A</span></a></li>
						<li><a href="board.jsp" class=""><i class="lnr lnr-code"></i>
								<span>자유 게시판</span></a></li>
						<li><a href="test.jsp" class=""><i class="lnr lnr-code"></i>
								<span>코딩 테스트</span></a></li>
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
					<h3 class="page-title">코딩 테스트</h3>
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h4 class="text-left">
								<span class="lnr lnr-home"></span>&nbsp;이현준
							</h4>
							<span class="panel-subtitle text-right">2021-09-23</span>
						</div>
						<div class="panel-heading">
							<h3 class="panel-title">계산기</h3>
							<p class="panel-subtitle">JAVA</p>
						</div>
						<div class="panel-body">
							<form method="post" action="formTest.jsp" name="detailTest">
								<textarea name="tContent" rows="6" class="form-control"
									style="resize: none;" readonly>Objectively network visionary methodologies via
best-of-breed users. Phosfluorescently initiate go forward
leadership skills before an expanded array of infomediaries.
Monotonectally incubate web-enabled communities rather than
process-centric.</textarea>
								<br>
								<h4>출력 예시</h4>
								<textarea name="tEx" rows="6" class="form-control"
									style="resize: none;"> 10 + 1
11</textarea>
								<br>
								<div class="panel panel-action">
									<div class="panel-heading">
										<h4>정답</h4>
										<div class="right">
											<button type="button" class="btn-toggle-collapse">
												<i class="lnr lnr-chevron-up lnr-chevron-down"></i>
											</button>
										</div>
									</div>
									<div class="panel-body panel-action-body">
										<textarea name="tAnswer" rows="6" class="form-control"
											style="resize: none;" readonly>Objectively network visionary methodologies via
best-of-breed users. Phosfluorescently initiate go forward
leadership skills before an expanded array of infomediaries.
Monotonectally incubate web-enabled communities rather than
process-centric.</textarea>
									</div>
								</div>

								<button type="submit" class="btn btn-default">글 수정</button>
							</form>

							<%-- <form method="post" action="form.jsp" name="detail">
								<input type="hidden" name="bTitle" value="${param.bTitle}"> 
								<input type="hidden" name="bWriter" value="${param.bWriter}">
								<input type="hidden" name="bCtgr" value="${param.bCtgr}">
								<input type="hidden" name="bLang" value="${param.bLang}">
								<textarea name="bContent" class="form-control" rows="6"
									style="resize: none;">${param.bContent}</textarea>
									<br>
								<button type="submit" class="btn btn-default">글 수정</button>
							</form> --%>
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

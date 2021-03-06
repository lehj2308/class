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
								<li><a href="#"><i class="lnr lnr-user"></i> <span>My
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
						<li><a href="test.jsp" class=""><i class="lnr lnr-code"></i>
								<span>?????? ?????????</span></a></li>
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
					<h3 class="page-title">???????????????</h3>
					<%-- <c:if test="${param.bCtgr==board}">
							<h3 class="panel-title">???????????????</h3>
							</c:if>
							<c:if test="${param.bCtgr==question}">
							<h3 class="panel-title">Q & A</h3>
							</c:if> --%>
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h4 class="text-left">
								<span class="lnr lnr-home"></span>&nbsp;?????????
							</h4>
							<span class="panel-subtitle text-right">2021-09-23</span>
						</div>
						<div class="panel-heading">
							<h3 class="panel-title">?????? ?????? ????????????????</h3>
							<p class="panel-subtitle">??????</p>
						</div>
						<div class="panel-body">
							<form method="post" action="form.jsp" name="detail">
								<textarea name="bContent" rows="6" class="form-control"
									style="resize: none;" readonly>Objectively network visionary methodologies via
best-of-breed users. Phosfluorescently initiate go forward
leadership skills before an expanded array of infomediaries.
Monotonectally incubate web-enabled communities rather than
process-centric.</textarea>
								<br>
								<button type="submit" class="btn btn-default">??? ??????</button>
							</form>

							<%-- <form method="post" action="form.jsp" name="detail">
								<input type="hidden" name="bTitle" value="${param.bTitle}"> 
								<input type="hidden" name="bWriter" value="${param.bWriter}">
								<input type="hidden" name="bCtgr" value="${param.bCtgr}">
								<input type="hidden" name="bLang" value="${param.bLang}">
								<textarea name="bContent" class="form-control" rows="6"
									style="resize: none;">${param.bContent}</textarea>
									<br>
								<button type="submit" class="btn btn-default">??? ??????</button>
							</form> --%>
						</div>
					</div>
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">??????</h3>
						</div>
						<div class="panel-body">
							<!-- ???????????? -->
							<form method="post" action="control.jsp" name="reply">
								<input type="hidden" name="action" value="??????">

								<div class="input-group">
									<input class="form-control" type="text" name="reply" required>
									<span class="input-group-btn"><button
											class="btn btn-primary" type="submit">?????? ??????</button></span>
								</div>
							</form>
							<!-- ???????????? END -->
							<br>
							<!-- ?????? ????????? -->
							<table class="table table-condensed">
								<tbody>
									<tr>
										<td>?????????</td>
										<td class="panel-action">
											<div class="panel-heading">
												<form method="post" action="control.jsp" name="replyUp">
												<input type="text" class="form-reply" name="reply" value="????????? ??????" required readonly>
												<div class="right">
													<button type="button" class="btn-update">????????????
													</button>
													<button type="button" class="btn-toggle-collapse">?????????
													</button>
												</div>
												</form>
											</div>
											<div class="panel-body panel-action-body">
												<!-- ????????? ?????? -->
												<form method="post" action="control.jsp" name="reply">
													<input type="hidden" name="action" value="?????????">
													<div class="input-group">
														<input class="form-control" type="text" name="reply"
															required> <span class="input-group-btn"><button
																class="btn btn-primary" type="submit">?????? ??????</button></span>
													</div>
												</form>
												<!-- ????????? ?????? END -->
												<br>
												<!-- ????????? ????????? -->
												<table class="table table-condensed">
													<tbody>
														<tr>
															<td>?????????</td>
															<td>??????</td>
															<td>2021-09-25</td>
														</tr>
													</tbody>
												</table>
												<!-- ????????? ????????? END-->
											</div>
										</td>
										<td>2021-09-25</td>
									</tr>
									<tr>
										<td>?????????</td>
										<td class="panel-action">
											<div class="panel-heading">
												<form method="post" action="control.jsp" name="replyUp">
												<input type="hidden" name="userID" value="?????????">
												<input type="text" class="form-reply" name="reply" value="????????? ??????" required readonly>
												<div class="right">
													<button type="button" class="btn-update">????????????
													</button>
													<button type="button" class="btn-toggle-collapse">?????????
													</button>
												</div>
												</form>
											</div>
											<div class="panel-body panel-action-body">
												<!-- ????????? ?????? -->
												<form method="post" action="control.jsp" name="reply">
													<input type="hidden" name="action" value="?????????">
													<div class="input-group">
														<input class="form-control" type="text" name="reply"
															required> <span class="input-group-btn"><button
																class="btn btn-primary" type="submit">?????? ??????</button></span>
													</div>
												</form>
												<!-- ????????? ?????? END -->
												<br>
												<!-- ????????? ????????? -->
												<table class="table table-condensed">
													<tbody>
														<tr>
															<td>?????????</td>
															<td><div class="panel-heading"><form method="post" action="control.jsp" name="rreplyUp">
												<input type="hidden" name="userID" value="?????????">
												<input type="text" class="form-reply" name="reply" value="??????" required readonly>
												<div class="right">
													<button type="button" class="btn-update">????????????
													</button>
													<button type="button" class="btn-toggle-collapse">?????????
													</button>
												</div>
												</form></div></td>
															<td>2021-09-25</td>
														</tr>
														<tr>
															<td>?????????</td>
															<td><div class="panel-heading"><form method="post" action="control.jsp" name="rreplyUp">
												<input type="hidden" name="userID" value="?????????">
												<input type="text" class="form-reply" name="reply" value="??????" required readonly>
												<div class="right">
													<button type="button" class="btn-update">????????????
													</button>
													<button type="button" class="btn-toggle-collapse">?????????
													</button>
												</div>
												</form></div></td>
															<td>2021-09-25</td>
														</tr>
														<tr>
															<td>?????????</td>
															<td><div class="panel-heading"><form method="post" action="control.jsp" name="rreplyUp">
												<input type="hidden" name="userID" value="?????????">
												<input type="text" class="form-reply" name="reply" value="??????" required readonly>
												<div class="right">
													<button type="button" class="btn-update">????????????
													</button>
													<button type="button" class="btn-toggle-collapse">?????????
													</button>
												</div>
												</form></div></td>
															<td>2021-09-25</td>
														</tr>
													</tbody>
												</table>
												<!-- ????????? ????????? END-->
											</div>
										</td>
										<td>2021-09-25</td>
									</tr>
								</tbody>
							</table>
							<!-- ?????? ????????? END -->
						</div>
					</div>
					<!-- END CONDENSED TABLE -->
				</div>
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

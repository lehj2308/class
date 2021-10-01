<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!doctype html>
<html lang="ko">

<head>
<title>Add-On 마이페이지</title>
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
		<!-- 상단 바 -->
		<mytag:navbar userName="${user.name}" />
		<!-- 왼쪽 사이드 바 -->
		<mytag:sidebar ctgr='' />
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel panel-profile">
						<div class="clearfix">
							<!-- 프로필 -->
							<div class="profile-left">
								<!-- 이름 -->
								<div class="profile-header">
									<div class="overlay"></div>
									<div class="profile-main">
										<h1 class="img-circle lnr lnr-home"></h1>
										<h3 class="name">${user.name}</h3>
									</div>
								</div>
								<!-- 이름 END -->
								<!-- 내 정보 -->
								<div class="profile-detail">
									<div class="profile-info">
										<h4 class="heading">Basic Info</h4>
										<ul class="list-unstyled list-justify">
											<li>성별<span>${user.gender}</span></li>
											<li>생년월일 <span>${user.birth}</span></li>
											<li>휴대폰 번호 <span>${user.phone}</span></li>
											<li>이메일 <span>${user.email}</span></li>
										</ul>
									</div>
									<div class="text-center">
										<a href="#" class="btn btn-primary">프로필 수정하기</a>
									</div>
								</div>
								<!-- 내 정보 END -->
							</div>
							<!-- 프로필 END -->
							<!-- 내가 쓴 게시물 및  댓글 -->
							<div class="profile-right">
								<!-- 게시물 종류 버튼 -->
								<div class="custom-tabs-line tabs-line-bottom left-aligned">
									<ul class="nav">
										<li><a href="myPage.do?myList=question">Q & A</a></li>
										<li><a href="myPage.do?myList=board">게시글</a></li>
										<li><a href="myPage.do?myList=test">코딩문제</a></li>
									</ul>
								</div>
								<!-- 게시물 종류 버튼 END -->
								<!-- 게시물 리스트 -->
								<div class="awards">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>번호</th>
												<th>제목</th>
												<th>작성날짜</th>
											</tr>
										</thead>
										<tbody>


											<c:choose>
												<c:when test="${param.myList eq 'test'}">
													<c:forEach var="v" items="${myList}">
														<tr>
															<td>${v.tId}</td>
															<td><a
																href="control.jsp?action='게시판글보기'&bId=${v.tId}">${v.tTitle}</a></td>
															<td>${v.tDate}</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>

													<c:forEach var="v" items="${myList}">
														<tr>
															<td>${v.bId}</td>
															<td><a
																href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.bTitle}</a></td>
															<td>${v.bDate}</td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
									<div class="text-center">
										<a href="#" class="btn btn-default">더보기</a>
									</div>
								</div>
								<!-- 게시물 리스트 END -->
								<!-- 댓글 리스트 -->
								<h4 class="heading">댓글</h4>
								<div class="awards">

									<table class="table table-striped">
										<thead>
											<tr>
												<th>번호</th>
												<th>내용</th>
												<th>작성날짜</th>
											</tr>
										</thead>
											<%-- 
									<c:forEach var="v" items="${댓글 리스트}">
										<tr>
											<td>${v.rId}</td>
											<td><a href="control.jsp?action='게시판글보기'&bId=${v.bId}">${v.rTitle}</a></td>
											<td>${v.rDate}</td>
										</tr>
									</c:forEach>
									 --%>
										</tbody>
									</table>
									<div class="text-center">
										<a href="#" class="btn btn-default">더보기</a>
									</div>
								</div>
								<!-- 댓글 리스트 END -->
							</div>
							<!-- 내가 쓴 게시물 및  댓글 END -->
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

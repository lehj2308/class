<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko" class="fullscreen-bg">

<head>
<title>Add-On 회원가입</title>
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
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box lockscreen clearfix">
					<div class="content">
						<h1 class="sr-only">Klorofil - Free Bootstrap dashboard</h1>
						<div class="logo text-center">
							<img src="assets/img/logo.png" alt="Klorofil Logo">
						</div>
						<form action="control.jsp" method="post" name="join">
							<input type="hidden" name="action" value="join">
							<div class="form-group">
								<span>이름</span> <input class="form-control" type="text"
									name="userName" required>
							</div>
							<div class="form-group">
								<span>성별</span> <br> <label class="fancy-radio"> <input
									name="gender" value="M" type="radio"> <span><i></i>남성</span>
								</label> <label class="fancy-radio"> <input name="gender"
									value="F" type="radio"> <span><i></i>여성</span>
								</label>
							</div>
							<div class="form-group">
								<span>아이디</span> <input class="form-control" type="text"
									name="id" required>
							</div>
							<div class="form-group">
								<span>비밀번호</span> <input class="form-control" type="password"
									name="pw" required>
							</div>
							<div class="form-group">
								<span>아이콘</span> <br> <input type="radio" name="iconId"
									value="?" required><span class="lnr lnr-rocket"></span>
								&nbsp;<input type="radio" name="iconId" value="?"><span
									class="lnr lnr-magic-wand"></span> &nbsp;<input type="radio"
									name="iconId" value="?"><span class="lnr lnr-heart"></span>
							</div>
							<div class="form-group">
								<span>생년월일</span> <input class="form-control" type="date"
									name="birth" required>
							</div>
							<div class="form-group">
								<span>휴대폰 번호</span> <input class="form-control" type="text"
									name="phone" onkeypress="inNumber();" maxlength="11" required>
							</div>
							<div class="form-group">
								<span>주소</span> <input class="form-control" type="text"
									name="userId" required>
							</div>
							<div class="form-group">
								<span>이메일</span> <input class="form-control" type="email"
									name="email" required>
							</div>
							<br>
							<div class="form-group">
								<input class="btn btn-primary btn-block" type="submit"
									value="회원가입">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<script type="text/javascript">
		function inNumber() {
			if (event.keyCode<48 || event.keyCode>57) {
				event.returnValue = false;
			}
		}

		function del() {
			result = confirm("탈퇴하시겠습니까?");
			if (result == true) {

				document.edit.action.value = "회원탈퇴";
				document.edit.submit();
			} else {
				return;
			}
		}
	</script>
</body>
</html>
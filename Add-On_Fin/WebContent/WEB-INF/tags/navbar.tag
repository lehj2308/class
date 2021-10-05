<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="userName"%>
<%@ attribute name="userNum"%>

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
			<c:if test="${empty userName}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span>로그인</span> <i
							class="icon-submenu lnr lnr-chevron-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="login.jsp"><i class="lnr lnr-user"></i> <span>로그인</span></a></li>
							<li><a href="join.jsp"><i class="lnr lnr-exit"></i> <span>회원가입</span></a></li>
						</ul></li>
				</ul>
			</c:if>
			<c:if test="${!empty userName}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span>${userName} 님</span> <i
							class="icon-submenu lnr lnr-chevron-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="myPage.do?selUserNum=${userNum}&myList=question"><i class="lnr lnr-user"></i> <span>My
										Profile</span></a></li>
							<li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
						</ul></li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>
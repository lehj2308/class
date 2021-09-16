<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="main.css">
<script type="text/javascript">
	function showPopup() {
		window.open("join.jsp", "join",
				"width=400, height=300, left=100, top=50");
	}
</script>

<body>

	<div class="content">
		<ol>
			<li><a href="control.jsp?action=main">전체목록보기</a></li>
		</ol>
		<hr>
		<mytag:login />

		<hr>

		<h2>전체목록</h2>
		<c:forEach var="v" items="${datas}">
			<c:set var="m" value="${v.m}" />
			<h3>
				[${m.unum}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글
				${m.replycount} | ${m.mdate}]
				<mytag:msg mnum="${m.mnum}" unum="${m.unum}" />
			</h3>
			<ol>
				<c:forEach var="r" items="${v.rlist}">
					<li>${r.unum}>> ${r.rmsg} [${r.rdate}] 
					<mytag:reply mnum="${r.mnum}" rnum="${r.rnum}" unum="${r.unum}" />
					</li>
				</c:forEach>
			</ol>

			<mytag:insert type="rmsg" mnum="${m.mnum}"/>
			<hr>
		</c:forEach>
		<a
			href="control.jsp?action=main&mcnt=${mcnt+1}&selUser=${param.selUser}">더보기</a>
			
		<hr>
		
		<mytag:insert type="msg" />

		<hr>

		<h4>신규 회원 목록</h4>
		<ol>
			<c:forEach var="u" items="${newUsers}">
				<li><a
					href="control.jsp?action=main&mcnt=${mcnt}&selUser=${u.unum}">${u.name}</a>님
					가입</li>
			</c:forEach>
		</ol>
	</div>
	
<div class="ad">
	<ul>
		<li><a href="https://www.instagram.com/hide_me_plz/?hl=ko"><img alt="광고_하이드미플리즈" src="img/cafe1.jpg" style="width:300px;height:300px;"></a></li>
		<li><a href="https://www.instagram.com/dangaotang/"><img alt="광고_단고당" src="img/cafe2.jpg" style="width:300px;height:300px;"></a></li>
		<li><a href="https://www.instagram.com/jamae.cake/"><img alt="광고_자매케이크" src="img/cafe3.jpg" style="width:300px;height:300px;"></a></li>
	</ul>
</div>
</body>
</html>
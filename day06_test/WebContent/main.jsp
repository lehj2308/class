<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.message.*,java.util.*"%>
<jsp:useBean id="datas" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 화면</title>
<script type="text/javascript">
	function check(mnum){
		pw=prompt("글 정보 변경을 위해 비밀번호를 입력하시오.");
		document.location.href="control.jsp?action=edit&mnum="+mnum+"&pw="+pw;
	}
</script>
</head>
<body>

<h2>게시글 목록</h2>
<hr>
<a href="msg.jsp">글 등록</a>
<hr>
<table border="1">
	<tr>
		<th>글 번호</th><th>제목</th><th>작성자</th>
	</tr>
	<%
		for(MessageVO vo:(ArrayList<MessageVO>)datas){
	%>
		<tr>
			<td><a href="javascript:check(<%=vo.getMnum()%>)"><%=vo.getMnum()%></a></td>
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getWriter()%></td>
		</tr>
	<%
		}
	%>
</table>
<hr>
<%
	if(session.getAttribute("mem")==null){
%>
		<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="login">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mid"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" value="로그인"></td>
			</tr>
		</table>
		</form>
		<a href="mem.jsp">회원가입</a>
<%
	}
	else{
%>
	<form action="control.jsp" method="post" name="form1">
		<input type="hidden" name="action" value="logout">
		<input type="submit" value="로그아웃">
	</form>
<%
	}
%>

</body>
</html>
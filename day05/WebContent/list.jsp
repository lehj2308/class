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
		writer=prompt("글 정보 변경을 위해 작성자명을 입력하시오.");
		document.location.href="control.jsp?action=edit&mnum="+mnum+"&writer="+writer;
	}
</script>
</head>
<body>

<h2>게시글 목록</h2>
<hr>
<a href="form.jsp">글 등록</a>
<hr>
<table border="1">
	<tr>
		<th>글 번호</th><th>제목</th><th>작성자</th><th>작성일</th>
	</tr>
	<%
		for(MessageVO vo:(ArrayList<MessageVO>)datas){
	%>
		<tr>
		<!-- "control.jsp?action=edit&mnum=vo.getMnum()" -->
			<td><a href="javascript:check(<%=vo.getMnum()%>)"><%=vo.getMnum()%></a></td><!-- 글 변경을 위한 비밀번호 등의 인증작업처리 필요! -->
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getWriter()%></td>
			<td><%=vo.getWdate()%></td>
		</tr>
	<%
		}
	%>
</table>

</body>
</html>
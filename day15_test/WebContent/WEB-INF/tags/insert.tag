<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="type"%>
<%@ attribute name="mnum"%>

<c:choose>
	<c:when test="${type=='rmsg'}">
		<c:if test="${user.unum != null}">
			<h3>댓글</h3>
			<form action="control.jsp" method="post" name="${mnum}">
				<input type="hidden" name="action" value="reply"> <input
					type="hidden" name="mnum" value="${mnum}"> <input
					type="hidden" name="unum" value="${user.unum}"> <input
					type="hidden" name="mcnt" value="${mcnt}"> <input
					type="text" name="rmsg" required> <input type="submit"
					value="입력">
			</form>
		</c:if>
	</c:when>

	<c:when test="${type=='msg'}">
		<c:if test="${user.unum != null}">
			<h3>게시글</h3>
			<form action="control.jsp" method="post" name="msg">
				<input type="hidden" name="action" value="message"> <input
					type="hidden" name="unum" value="${user.unum}"> <input
					type="hidden" name="mcnt" value="${mcnt}"> <input
					type="text" name="msg" required> <input type="submit"
					value="입력">
			</form>
		</c:if>
	</c:when>

</c:choose>
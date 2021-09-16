<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mnum" %>
<%@ attribute name="rnum" %>
<%@ attribute name="unum" %>

<c:if test="${unum == user.unum}">
	<button type="button" onclick="location.href='control.jsp?action=replyDelete&mnum=${mnum}&rnum=${rnum}&mcnt=${mcnt}'">삭제</button>
</c:if>
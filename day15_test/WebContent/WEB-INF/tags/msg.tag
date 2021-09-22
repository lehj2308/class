<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="mnum" %>
<%@ attribute name="unum" %>

<button type="button" onclick="location.href='messageUpdate.do?mnum=${mnum}&mcnt=${mcnt}'">좋아요</button>
<c:if test="${unum == user.unum}">
	<button type="button" onclick="location.href='messageDelete.do?mnum=${mnum}&mcnt=${mcnt}'">삭제</button>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.message.*"
	errorPage="error.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO" />

<%
	String action = request.getParameter("action");
	if (action.equals("list")) {
		ArrayList<MessageVO> datas = messageDAO.getDBList();
		request.setAttribute("datas", datas);
		pageContext.forward("list.jsp");
	} else if (action.equals("insert")) {
		if (messageDAO.insertDB(messageVO)) {
			response.sendRedirect("control.jsp?action=list");
		}

		else {
			throw new Exception("DB 추가 오류 발생!");
		}
	} else if (action.equals("delete")) {
		if (messageDAO.deleteDB(messageVO)) {
			response.sendRedirect("control.jsp?action=list");
		}

		else {
			throw new Exception("DB 삭제 오류 발생!");
		}
	} else if (action.equals("update")) {
		if (messageDAO.updateDB(messageVO)) {
			response.sendRedirect("control.jsp?action=list");
		} else {
			throw new Exception("DB 변경 오류 발생!");
		}
	} else if (action.equals("edit")) {
		MessageVO data = messageDAO.getDBData(messageVO);
		request.setAttribute("data", data);
		pageContext.forward("edit.jsp");

	} else {
		out.println("<script>alert('파라미터 확인!');history.go(-1)</script>");
		//throw new Exception("파라미터 확인!");
	}
%>

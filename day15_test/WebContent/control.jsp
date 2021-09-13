<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, model.message.*, model.users.*"%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO" />
<jsp:useBean id="replyDAO" class="model.message.ReplyDAO" />
<jsp:useBean id="replyVO" class="model.message.ReplyVO" />
<jsp:setProperty property="*" name="replyVO" />

<%
	String action = request.getParameter("action");
	String url = "control.jsp?action=main";
	String mcntt = request.getParameter("mcnt");
	int mcnt = 2;
	if (mcntt != null) {
		mcnt = Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" + mcnt;
	String selUsers = request.getParameter("selUsers");
	if (selUsers != null) {
		url = url + "&selUsers=" + selUsers;
	}

	if (action.equals("main")) {
		ArrayList<MessageSet> datas = messageDAO.selectAll(selUsers, mcnt);

		request.setAttribute("datas", datas);
		request.setAttribute("selUsers", selUsers);
		request.setAttribute("mcnt", mcnt);

		pageContext.forward("main.jsp");
	}

	else if (action.equals("reply")) {
		if (replyDAO.replyInsert(replyVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("reply 추가 오류 발생!");
		}
	}
%>
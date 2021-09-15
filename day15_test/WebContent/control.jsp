<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, model.message.*, model.users.*"%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO" />
<jsp:useBean id="replyDAO" class="model.message.ReplyDAO" />
<jsp:useBean id="replyVO" class="model.message.ReplyVO" />
<jsp:setProperty property="*" name="replyVO" />
<jsp:useBean id="usersDAO" class="model.users.UsersDAO" />
<jsp:useBean id="usersVO" class="model.users.UsersVO" />
<jsp:setProperty property="*" name="usersVO" />


<%
	String action = request.getParameter("action");
	String url = "control.jsp?action=main";
	String mcntt = request.getParameter("mcnt");
	int mcnt = 1;
	if (mcntt != null) {
		mcnt = Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" + mcnt;
	
	String selUser = request.getParameter("selUser");
	if (selUser != null) {
		url = url + "&selUser=" + selUser;
	}
	
	

	if (action.equals("main")) {
		ArrayList<MessageSet> datas = messageDAO.selectAll(selUser, mcnt);

		request.setAttribute("datas", datas);
		request.setAttribute("selUser", selUser);
		request.setAttribute("mcnt", mcnt);

		pageContext.forward("main.jsp");
	}
	
	else if (action.equals("message")) {
		if (messageDAO.messageInsert(messageVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("message 추가 오류 발생!");
		}
	}
	else if (action.equals("messageDelete")) {
		if (messageDAO.messageDelete(messageVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("message 삭제 오류 발생!");
		}
	}
	
	

	else if (action.equals("reply")) {
		if (replyDAO.replyInsert(replyVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("reply 추가 오류 발생!");
		}
	}
	else if (action.equals("replyDelete")) {
		if (replyDAO.replyDelete(replyVO)) {
			response.sendRedirect(url);
		} else {
			throw new Exception("reply 삭제 오류 발생!");
		}
	}

	else if (action.equals("login")) {
		UsersVO user = usersDAO.login(usersVO);
		if (user.getName() != null) {
			session.setAttribute("user", user);
			response.sendRedirect(url);
		} else {
			out.println("<script>alert('로그인 정보를 확인하세요!');history.go(-1)</script>");
		}
	}

	else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	}

	else if (action.equals("join")) {
		if (usersDAO.join(usersVO)) {
			out.println("<script>alert('회원가입 완료!');window.close();</script>");
		} else {
			throw new Exception("join 오류 발생!");
		}
	}
	
	
%>
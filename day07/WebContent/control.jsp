<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,model.message.*,model.member.*"
	errorPage="error.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="messageDAO" class="model.message.MessageDAO" />
<jsp:useBean id="messageVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="messageVO" />
<jsp:useBean id="memberDAO" class="model.member.MemberDAO" />
<jsp:useBean id="memberVO" class="model.member.MemberVO" scope="session" />
<jsp:setProperty property="*" name="memberVO" />


<%
	String action = request.getParameter("action");

	if (action.equals("list")) {
		ArrayList<MessageVO> datas = messageDAO.getDBList();
		request.setAttribute("datas", datas);
		pageContext.forward("list.jsp");

	} else if (action.equals("myList")) {
		String userID=memberVO.getUserID();
		ArrayList<MessageVO> datas = messageDAO.getDBList(userID);
		request.setAttribute("datas", datas);
		pageContext.forward("list.jsp");

	} else if (action.equals("insert")) {
		if (messageDAO.insertDB(messageVO)) {
			response.sendRedirect("control.jsp?action=list");
		} else {
			throw new Exception("DB 추가 오류 발생!");
		}

	} else if (action.equals("delete")) {
		if (messageDAO.deleteDB(messageVO)) {
			response.sendRedirect("control.jsp?action=list");
		} else {
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

	} else if (action.equals("login")) {
		if (memberDAO.loginDB(memberVO)) {
			pageContext.forward("control.jsp?action=list");
		} else {
			out.println("<script>alert('로그인 정보를 확인하세요!');history.go(-1)</script>");
		}

	} else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("index.html");

	} else if (action.equals("signup")) {
		if (memberDAO.signupDB(memberVO)) {
			response.sendRedirect("index.html");
		}

		else {
			out.println("<script>alert('중복 아이디 입니다.');history.go(-1)</script>");
		}
	}

	else {
		out.println("<script>alert('파라미터 확인!');history.go(-1)</script>");
		//throw new Exception("파라미터 확인!");
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.member.*, model.message.*, java.util.*"%>
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
	System.out.println("이거"+memberVO);
	String action = request.getParameter("action");

	if (action.equals("main")) {
		System.out.println(memberVO);
		ArrayList<MessageVO> datas = messageDAO.getList();
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}

	else if (action.equals("myList")) {
		String userID = memberVO.getUserID();
		ArrayList<MessageVO> datas = messageDAO.searchList(userID);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}

	else if (action.equals("searchList")) {
		String search = request.getParameter("search");
		ArrayList<MessageVO> datas = messageDAO.searchList(search);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}
	
	else if(action.equals("content")){
		System.out.println(messageVO);
		MessageVO data=messageDAO.content(messageVO);
		request.setAttribute("data", data);
		pageContext.forward("content.jsp");
	}
	
	else if(action.equals("updateDB")){
		System.out.println(messageVO);
		if (messageDAO.updateDB(messageVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 수정 오류 발생!");
		}
	}
	
	else if(action.equals("insertDB")){
		System.out.println(messageVO);
		if (messageDAO.insertDB(messageVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 추가 오류 발생!");
		}
	}
	
	else if(action.equals("deleteDB")){
		System.out.println(messageVO);
		if (messageDAO.deleteDB(messageVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 삭제 오류 발생!");
		}
	}
	
	
	
	
	

	else if (action.equals("login")) {
		if (memberDAO.login(memberVO)!=null) {
			memberVO=memberDAO.login(memberVO);
			session.setAttribute("memberVO", memberVO);
			System.out.println(memberVO);
			pageContext.forward("control.jsp?action=main");
		} else {
			out.println("<script>alert('로그인 정보를 확인하세요!');history.go(-1)</script>");
		}
		System.out.println("로그인"+memberVO);
	}

	else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	}

	else if (action.equals("signup")) {
		if (memberDAO.signup(memberVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			out.println("<script>alert('존재하는 ID입니다.');history.go(-1)</script>");
			session.invalidate();
		}
	}
	
	
	else if (action.equals("check")){
		String checkPW=request.getParameter("checkPW");
		if (memberVO.getUserPW().equals(checkPW)) {
			pageContext.forward("mypage.jsp");
		}
		else{
			out.println("<script>alert('비밀번호가 틀렸습니다.');history.go(-1)</script>");
		}
	}

	else if (action.equals("userUpdate")) {
		String newPW=request.getParameter("newPW");
		if (memberDAO.userUpdate(memberVO,newPW)) {
			response.sendRedirect("control.jsp?action=main");
			session.invalidate();
		} else {
			throw new Exception("user 수정 오류 발생!");
		}
	}

	else if (action.equals("userDelete")) {
		if (memberDAO.userDelete(memberVO)) {
			session.invalidate();
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("user 삭제 오류 발생!");
		}
	}
%>
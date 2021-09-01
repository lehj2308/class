<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,model.message.*,model.member.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="msgDAO" class="model.message.MessageDAO" />
<jsp:useBean id="memDAO" class="model.member.MemberDAO" />
<jsp:useBean id="memVO" class="model.member.MemberVO" />
<jsp:setProperty property="*" name="memVO" />
<jsp:useBean id="msgVO" class="model.message.MessageVO" />
<jsp:setProperty property="*" name="msgVO" />
<%
	String action = request.getParameter("action");

	if (action.equals("main")) {
		ArrayList<MessageVO> datas = msgDAO.getDBList();
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	} else if (action.equals("login")) {
		MemberVO mem = memDAO.getDBData(memVO);
		if (mem != null) {
			session.setAttribute("mem", mem);
			response.sendRedirect("control.jsp?action=main");
		} else {
			out.println("<script>alert('로그인 실패!');history.go(-1)</script>");
		}
	} else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("control.jsp?action=main");
	} else if (action.equals("insertMem")) {
	} else if (action.equals("insertMsg")) {
		if(msgDAO.insertDB(msgVO)){
			response.sendRedirect("control.jsp?action=main");
		}
		else{
			throw new Exception("DB 추가 오류 발생!");
		}
	} else if (action.equals("edit")) {
		if(session.getAttribute("mem")==null){
			out.println("<script>alert('로그인하세요!');history.go(-1)</script>");
		}
		else{
			MemberVO vo=(MemberVO)session.getAttribute("mem");
			if(vo.getMpw().equals(request.getParameter("pw"))){
				// msg-writer == 세션에 등록된 mem의 mid
				MessageVO data=msgDAO.getDBData(msgVO);
				request.setAttribute("data", data);
				pageContext.forward("edit.jsp");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
%>
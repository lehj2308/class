<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.aBank.*, model.bBank.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="aBankDAO" class="model.aBank.ABankDAO" />
<jsp:useBean id="bBankDAO" class="model.bBank.BBankDAO" />

<%
	String action= request.getParameter("action");

	if(action.equals("main")){
		ABankVO aBankVO=aBankDAO.getABank();
		BBankVO bBankVO=bBankDAO.getBBank();
		request.setAttribute("aBankVO", aBankVO);
		request.setAttribute("bBankVO", bBankVO);
		pageContext.forward("main.jsp");
	}
	
	else if(action.equals("aTrans")){
		int bal=Integer.parseInt(request.getParameter("bal"));
		if(aBankDAO.aTrans(bal)){
			out.println("<script>alert('완료');location.href='control.jsp?action=main';</script>");
		}
		else{
			out.println("<script>alert('잔액부족');location.href='control.jsp?action=main';</script>");
		}

	}
	
	else if(action.equals("bTrans")){
		int bal=Integer.parseInt(request.getParameter("bal"));
		if(bBankDAO.bTrans(bal)){
			out.println("<script>alert('완료');location.href='control.jsp?action=main';</script>");
		}
		else{
			out.println("<script>alert('잔액부족');location.href='control.jsp?action=main';</script>");
		}
	}

%>
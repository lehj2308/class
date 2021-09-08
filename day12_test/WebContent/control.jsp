<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import=" model.coffee.*, java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="coffeeDAO" class="model.coffee.CoffeeDAO" />
<jsp:useBean id="coffeeVO" class="model.coffee.CoffeeVO" />
<jsp:setProperty property="*" name="coffeeVO" />

<%
	String action = request.getParameter("action");

	if (action.equals("main")) {
		ArrayList<CoffeeVO> datas = coffeeDAO.selectAll();
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	}

	else if (action.equals("edit")) {
		System.out.println("c.edit" + coffeeVO);
		CoffeeVO data = coffeeDAO.selectOne(coffeeVO);
		System.out.println("data" + data);
		request.setAttribute("data", data);
		pageContext.forward("edit.jsp");
	}

	else if (action.equals("update")) {
		System.out.println(coffeeVO);
		if (coffeeDAO.update(coffeeVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 수정 오류 발생!");
		}
	}

	else if (action.equals("insert")) {
		System.out.println(coffeeVO);
		if (coffeeDAO.insert(coffeeVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 추가 오류 발생!");
		}
	}

	else if (action.equals("delete")) {
		System.out.println(coffeeVO);
		if (coffeeDAO.delete(coffeeVO)) {
			response.sendRedirect("control.jsp?action=main");
		} else {
			throw new Exception("DB 삭제 오류 발생!");
		}
	}

	/* else if (action.equals("search")) {
		String search = request.getParameter("search");
		ArrayList<CoffeeVO> datas = coffeeDAO.search(search);
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
	} */
%>
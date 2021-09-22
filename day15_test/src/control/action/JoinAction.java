package control.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.UsersDAO;
import model.users.UsersVO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		
		
		UsersDAO usersDAO = new UsersDAO();
		UsersVO usersVO = new UsersVO();
		usersVO.setPasswd(request.getParameter("passwd"));
		usersVO.setUnum(request.getParameter("unum"));
		usersVO.setName(request.getParameter("name"));
		
		if (!usersDAO.join(usersVO)) {
			throw new IOException("join ���� �߻�!");
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>alert('ȸ������ �Ϸ�!');window.close();</script>");
		out.flush();

		return forward;
	}
}

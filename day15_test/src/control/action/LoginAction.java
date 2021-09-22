package control.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.users.UsersDAO;
import model.users.UsersVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����");
		
		
		// 1. uVO�� ������ִ� ���ڵ��� get()
		// 2. �α��� ����,���п��� �Ǵ�
		// 3. ����: ���Ǽ��� / ����: ��ũ��Ʈ ���
		UsersDAO usersDAO=new UsersDAO();
		UsersVO usersVO=new UsersVO();
		usersVO.setPasswd(request.getParameter("passwd"));
		usersVO.setUnum(request.getParameter("unum"));
		
		UsersVO user = usersDAO.login(usersVO);
		
		ActionForward forward = new ActionForward();
		if(user.getName() != null) {
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			forward.setRedirect(false);
			forward.setPath("main.do");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('�α��� ����!');history.go(-1);</script>");
			out.flush();
		}

		return forward;
	}

}

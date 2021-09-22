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
		System.out.println("오냐");
		
		
		// 1. uVO를 만들수있는 인자들을 get()
		// 2. 로그인 성공,실패여부 판단
		// 3. 성공: 세션세팅 / 실패: 스크립트 출력
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
			out.println("<script>alert('로그인 실패!');history.go(-1);</script>");
			out.flush();
		}

		return forward;
	}

}

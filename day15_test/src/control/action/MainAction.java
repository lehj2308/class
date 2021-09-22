package control.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.message.MessageDAO;
import model.message.MessageSet;
import model.users.UsersDAO;
import model.users.UsersVO;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		String mcntt = request.getParameter("mcnt");
		System.out.println("∏ﬁ¿Œ"+mcntt);
		int mcnt = 1;
		if (mcntt != null) {
			mcnt = Integer.parseInt(mcntt);
		}

		String selUser = request.getParameter("selUser");

		MessageDAO messageDAO = new MessageDAO();
		UsersDAO usersDAO = new UsersDAO();
		ArrayList<MessageSet> datas = messageDAO.selectAll(selUser, mcnt);
		ArrayList<UsersVO> newUsers = usersDAO.selectAll();

		request.setAttribute("datas", datas);
		request.setAttribute("selUser", selUser);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("mcnt", mcnt);

		forward.setRedirect(false);
		forward.setPath("main.jsp");

		return forward;
	}

}

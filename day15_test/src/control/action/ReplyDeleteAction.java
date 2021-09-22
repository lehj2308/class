package control.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.message.MessageDAO;
import model.message.MessageSet;
import model.message.ReplyDAO;
import model.message.ReplyVO;
import model.users.UsersDAO;
import model.users.UsersVO;

public class ReplyDeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		ReplyVO replyVO = new ReplyVO();
		replyVO.setMnum(Integer.parseInt(request.getParameter("mnum")));
		replyVO.setRnum(Integer.parseInt(request.getParameter("rnum")));

		ReplyDAO replyDAO = new ReplyDAO();

		if (!replyDAO.replyDelete(replyVO)) {
			throw new IOException("reply 삭제 오류 발생!");
		}

		String mcntt = request.getParameter("mcnt");
		System.out.println(mcntt+"mcntt");
		
		int mcnt = 1;
		if (mcntt != null) {
			mcnt = Integer.parseInt(mcntt);
		}
		System.out.println(mcnt+"mcnt");
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

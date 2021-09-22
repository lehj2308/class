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

public class ReplyAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		

		ReplyVO replyVO =new ReplyVO();
		replyVO.setMnum(Integer.parseInt(request.getParameter("mnum")));
		replyVO.setUnum(request.getParameter("unum"));
		replyVO.setRmsg(request.getParameter("rmsg"));
		
		ReplyDAO replyDAO = new ReplyDAO();

		if (!replyDAO.replyInsert(replyVO)) {
			throw new IOException("reply 추가 오류 발생!");
		}
		
		String mcntt = request.getParameter("mcnt");
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

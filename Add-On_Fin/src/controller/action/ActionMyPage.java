package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.board.MyReplySet;
import model.board.ReplyDAO;
import model.board.ReplyVO;
import model.test.TestMySet;
import model.test.TestReplyDAO;
import model.test.TestReplyVO;
import model.users.UsersDAO;
import model.users.UsersVO;

public class ActionMyPage implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		String myList = "question" ;//�ʱⰪ 
		
		if (request.getParameter("myList")!=null) {
			
			myList = request.getParameter("myList");
		};
		
		String path="";
		if (myList.equals("test")) {
			path += "test.do";
		} else if (myList.equals("board")) {
			path +="board.do";
			
		}else {
			path+="question.do";
		}
		
		
		forward.setPath(path);
		forward.setRedirect(false);
		 // ���������� ��� �κ�! 
		int pageNum=0;
		int userNum = Integer.parseInt(request.getParameter("selUserNum")) ;
		HttpSession session= request.getSession();
		UsersVO user =(UsersVO) session.getAttribute("user"); // �α����� ����
		UsersDAO userDAO = new UsersDAO();
		
		if (user ==null) { // �α����� ������ ���� ��
			
			UsersVO selUser = new UsersVO();
			selUser.setUserNum(userNum);
			selUser = userDAO.getDBData(selUser);
			
			request.setAttribute("selUser", selUser);
			return forward ;
		}
		if (user.getUserNum() != userNum) { // �α����� ������ ������ �ƴ� �ٸ� ����� ������������ �������� ��
			UsersVO selUser = new UsersVO();
			selUser.setUserNum(userNum);
			selUser = userDAO.getDBData(selUser);
			
			request.setAttribute("selUser", selUser);
			return forward;
		}
		// ������ ������ ���� ���� ��  ��� ����Ʈ�� ����! ------------------------------------------------
		
		if (request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		ReplyDAO replyDAO = new ReplyDAO();
		MyReplySet myReplySet = replyDAO.myReply(user, pageNum);
		ArrayList<ReplyVO> myReplies =  myReplySet.getRlist();
	//	int replyLen = myReplySet.getReplyCnt();
		
		request.setAttribute("myReplies", myReplies);
	//	request.setAttribute("replyLen", replyLen);
	//	System.out.println("ActionMyPage replyLen : "+replyLen);
		request.setAttribute("selUser", user);
		
		TestReplyDAO testReplyDAO = new TestReplyDAO();
		TestMySet testMySet = testReplyDAO.myReply(user, pageNum);
		ArrayList<TestReplyVO> myTestReplies = testMySet.getRlist();
		int replyLen = testMySet.getTestRecnt();
		request.setAttribute("myTestReplies", myTestReplies);
		request.setAttribute("replyLen", replyLen);
		
		
		
		
		
		return forward;
	}
	
}

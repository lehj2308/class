package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.test.TestDAO;
import model.test.TestReplyDAO;
import model.test.TestReplyVO;
import model.test.TestSet;
import model.test.TestVO;
import model.users.UsersVO;

public class ActionDetailTest implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		TestDAO testDAO = new TestDAO();
		TestReplyDAO testReplyDAO = new TestReplyDAO();
		TestVO test = new TestVO();
		TestReplyVO reply = new TestReplyVO();
		UsersVO user = new UsersVO();
		
		ArrayList<TestSet> testSets = null;
		
		int pageNum = 0; // 댓글 페이징 
		int pageLen = 7;
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int tId = Integer.parseInt(request.getParameter("tId"));
		test.settId(tId);
		
		test = testDAO.getDBData(test);
		
		reply.settId(tId);
		user.setUserNum(0);
		
		testSets = testReplyDAO.selectAll(user, pageNum, reply);
		
		
		
//		int hit = test.gettHit();  비지니스 메서드로 대체 
//		test.settHit(++hit);
//		if(testDAO.update(test)) {
//			System.out.println("조회수 증가!");
//		}
		request.setAttribute("test", test);
		request.setAttribute("testSets", testSets);
		request.setAttribute("pageLen", pageLen);
		
		
		
		forward = new ActionForward();
		forward.setPath("detailTest.jsp");
		forward.setRedirect(false);
		
		
		
		
		return forward;
	}

}

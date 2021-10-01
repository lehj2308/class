package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.test.TestDAO;
import model.test.TestVO;
import model.users.UsersVO;

public class ActionTest implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		TestDAO testDAO= new TestDAO();
		
		UsersVO userVO = new UsersVO();
		
		
		int pageNum=0; // 초기에는 pageNum =1 페이지
		if (request.getParameter("pageNum") !=null) {
			
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		}
		String content = "";
		if (request.getParameter("tTitle") != null) {
			content = request.getParameter("tTitle");
		}
		
		int pageLen = 6; // 총 길이를 구해줘야함  DAO 메서드 추가 
		
		userVO.setUserNum(0); // 비로그인 상태 
		
		
		ArrayList<TestVO> tests  = testDAO.getDBList(content, pageNum, userVO);
		
		request.setAttribute("tests", tests);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageLen", pageLen);
		
		
		forward.setPath("test.jsp");
		forward.setRedirect(false);
		
		
		
		
		
		return forward;
		
	}
	
}

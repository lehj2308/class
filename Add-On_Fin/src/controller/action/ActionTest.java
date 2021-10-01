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
		
		
		int pageNum=0; // �ʱ⿡�� pageNum =1 ������
		if (request.getParameter("pageNum") !=null) {
			
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		}
		String content = "";
		if (request.getParameter("tTitle") != null) {
			content = request.getParameter("tTitle");
		}
		
		int pageLen = 6; // �� ���̸� ���������  DAO �޼��� �߰� 
		
		userVO.setUserNum(0); // ��α��� ���� 
		
		
		ArrayList<TestVO> tests  = testDAO.getDBList(content, pageNum, userVO);
		
		request.setAttribute("tests", tests);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageLen", pageLen);
		
		
		forward.setPath("test.jsp");
		forward.setRedirect(false);
		
		
		
		
		
		return forward;
		
	}
	
}

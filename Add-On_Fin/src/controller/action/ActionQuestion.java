package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.board.BoardDAO;
import model.board.BoardVO;
import model.users.UsersVO;

public class ActionQuestion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		BoardDAO boardDAO = new BoardDAO();
		BoardVO boardVO = new BoardVO();
		UsersVO userVO = new UsersVO();
		
		int pageNum=0; // �ʱ⿡�� pageNum =1 ������
		if (request.getParameter("pageNum") !=null) {
			
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		}
		int pageLen = 6; // �� ���̸� ���������  DAO �޼��� �߰� 
		
		userVO.setUserNum(0);
		boardVO.setbCtgr("question");
		
		ArrayList<BoardVO> datas = boardDAO.getDBList(userVO, boardVO, pageNum);
		
		request.setAttribute("datas", datas);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageLen", pageLen);
		
		
		forward.setPath("question.jsp");
		forward.setRedirect(false);
		
		
		
		
		
		return forward;
	}
	

}

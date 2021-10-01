package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.board.BoardDAO;
import model.board.BoardVO;
import model.board.ReplyDAO;
import model.board.ReplySet;
import model.board.ReplyVO;
import model.users.UsersVO;

public class ActionDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		
		BoardDAO boardDAO = new BoardDAO();
		ReplyDAO replyDAO = new ReplyDAO();
		UsersVO userVO =  new UsersVO();
		ReplyVO replyVO = new ReplyVO();
		
		
		
		int bId = Integer.parseInt( request.getParameter("bId"));
		int pageNum = 0; // ��� ����¡
		int pageLen = 9; // ��� ����
		if( request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		BoardVO board = new BoardVO();
		board.setbId(bId);
		board = boardDAO.getDBData(board); 
		
		userVO.setUserNum(0);
		replyVO.setbId(bId);
		
		ArrayList<ReplySet> replySets = replyDAO.getDBList(userVO, replyVO, pageNum);
		
//		for (ReplySet v : replySets) {
//			ReplyVO rv = v.getRvo();
//			ArrayList<ReplyVO> rrList = v.getRrlist();
//			System.out.println("-----------------------");
//			System.out.println("��� : "+ rv.toString());
//			
//			System.out.println("���� : ");
//		if (rrList != null) {
//			for (ReplyVO vo : rrList) {
//				System.out.println(vo.getrContent());
//			}
//			
//			}
//			
//		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageLen", pageLen);
		request.setAttribute("board", board);
		request.setAttribute("replySets", replySets);
		System.out.println(board);
		
		
		forward = new ActionForward();
		forward.setPath("detail.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}

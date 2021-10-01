package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.board.BoardDAO;
import model.board.BoardVO;
import model.users.UsersVO;

public class ActionBoard implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		
		BoardDAO boardDAO = new BoardDAO();
		
		UsersVO userVO = new UsersVO();
		userVO.setUserNum(0);
		BoardVO boardVO = new BoardVO();
		int announcePageNum =0;
		int boardPageNum=0;
		int announceLen = 3;
		int boardLen = 5;
		String title ="";
		if (request.getParameter("announcePageNum") !=null) {
			announcePageNum = Integer.parseInt(request.getParameter("announcePageNum"));
			
		}
		if (request.getParameter("boardPageNum") !=null) {
			boardPageNum = Integer.parseInt(request.getParameter("boardPageNum"));
			
		}
		if (request.getParameter("title") !=null) {
			title = request.getParameter("title");
		}
		System.out.println(announcePageNum);
		boardVO.setbCtgr("announce");
		
		ArrayList<BoardVO> announceList = boardDAO.getDBList(userVO, boardVO, announcePageNum);
		System.out.println("announceListLength "+announceList.size());
		
		boardVO.setbCtgr("board");
		boardVO.setbTitle(title);
		
		ArrayList<BoardVO> boardList = boardDAO.getDBList(userVO, boardVO, boardPageNum);
		
		
		
		request.setAttribute("announceList", announceList);
		request.setAttribute("boardList", boardList);
		request.setAttribute("announcePageNum", announcePageNum);
		request.setAttribute("boardPageNum", boardPageNum);
		request.setAttribute("announceLen", announceLen);
		request.setAttribute("boardLen",boardLen);
		
		forward = new ActionForward();
		forward.setPath("board.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}
	

}

package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import model.tBoard.TBoardDAO;
import model.tBoard.TBoardVO;

public class DeleteTBoardController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TBoardDAO dao = new TBoardDAO();
		TBoardVO vo = new TBoardVO();
		
		ModelAndView mav= new ModelAndView();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		vo.setId(id);
		dao.deleteTBoard(vo);
		
		mav.setViewName("redirect:main.do");
		return mav;
	}

}

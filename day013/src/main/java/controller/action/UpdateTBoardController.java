package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import model.tBoard.TBoardDAO;
import model.tBoard.TBoardVO;

public class UpdateTBoardController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TBoardDAO dao = new TBoardDAO();
		TBoardVO vo = new TBoardVO();

		ModelAndView mav= new ModelAndView();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int id = Integer.parseInt(request.getParameter("id"));

		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		dao.updateTBoard(vo);

		mav.setViewName("redirect:main.do");

		return mav;
	}

}

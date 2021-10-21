package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import model.tBoard.TBoardDAO;
import model.tBoard.TBoardVO;

public class MainController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		TBoardDAO dao = new TBoardDAO();
		TBoardVO vo = new TBoardVO();
		
		ModelAndView mav= new ModelAndView();
		
		int page = 1;
		if(request.getParameter("page")!=null && Integer.parseInt(request.getParameter("page"))!=0) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		List<TBoardVO> tBoardList = dao.getTBoardList(vo,page);
		
		mav.addObject("tBoardList", tBoardList);
		mav.addObject("page", page);
		mav.setViewName("main");
		
		return mav;
	}

}

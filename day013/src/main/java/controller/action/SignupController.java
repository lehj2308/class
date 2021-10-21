package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import model.tMember.TMemberDAO;
import model.tMember.TMemberVO;

public class SignupController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TMemberDAO dao = new TMemberDAO();
		TMemberVO vo = new TMemberVO();
		
		ModelAndView mav= new ModelAndView();
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		
		dao.insertTMember(vo);
		
		mav.setViewName("redirect:index.jsp");
		
		return mav;
	}

}

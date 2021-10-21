package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		ModelAndView mav= new ModelAndView();
		
		HttpSession session = request.getSession();

		session.invalidate();

		mav.setViewName("redirect:index.jsp");
		
		return mav;
	}

}

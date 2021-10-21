package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import model.tMember.TMemberDAO;
import model.tMember.TMemberVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TMemberDAO dao = new TMemberDAO();
		TMemberVO vo = new TMemberVO();
		
		ModelAndView mav= new ModelAndView();
		
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		vo.setId(id);
		vo.setPassword(password);
		
		if(dao.getTMember(vo)==null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 실패');history.go(-1)</script>");
				mav.setViewName("redirect:index.jsp");
				return mav;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		vo = dao.getTMember(vo);
		HttpSession session = request.getSession();
		session.setAttribute("user", vo);

		mav.setViewName("redirect:main.do");
		return mav; // DS->HM->C->VR
	}

}

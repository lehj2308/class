package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.tMember.TMemberServiceImpl;
import model.tMember.TMemberVO;

@Controller
public class TMemberController {

	@Autowired
	private TMemberServiceImpl tMemberService;
	
	// 1. POJO 클래스
	// 2. 상속xxx, 추상클래스의 강제선xxx -> 함수의 input,output,함수면 변경가능

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginPrint(@ModelAttribute("guest")TMemberVO vo) {
		vo.setId("test");
		vo.setPassword("1234");
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(HttpServletRequest request, TMemberVO vo) throws Exception {
		
		if(vo.getId().equals("") || vo.getId()==null) {
			throw new Exception("로그인 빈칸");
		}
		if (tMemberService.getTMember(vo) == null) {
			return "redirect:login.jsp";
		}
		vo = tMemberService.getTMember(vo);
		HttpSession session = request.getSession();
		session.setAttribute("user", vo);
		return "redirect:main.do"; // DS->HM->C->VR
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index.jsp";
	}
	
	@RequestMapping("/signup.do")
	public String signup(TMemberVO vo) {
		tMemberService.insertTMember(vo);
		return "redirect:login.jsp";
	}
}

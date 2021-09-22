package control.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.action.ActionForward;
import control.action.JoinAction;
import control.action.LoginAction;
import control.action.LogoutAction;
import control.action.MainAction;
import control.action.MessageAction;
import control.action.MessageDeleteAction;
import control.action.MessageUpdateAction;
import control.action.ReplyAction;
import control.action.ReplyDeleteAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 1) 사용자의 요청을 분석
		String uri=request.getRequestURI();
		String cp=request.getContextPath();
		String action=uri.substring(cp.length());
		System.out.println(action);
		
		ActionForward forward=null;
		// 2) 컨트롤러랑 매핑
		if(action.equals("/main.do")) {
			forward=new MainAction().execute(request, response);
		}
		else if(action.equals("/message.do")) {
			forward=new MessageAction().execute(request, response);
		}
		else if(action.equals("/messageDelete.do")) {
			forward=new MessageDeleteAction().execute(request, response);
		}
		else if(action.equals("/messageUpdate.do")) {
			forward=new MessageUpdateAction().execute(request, response);
		}
		else if(action.equals("/reply.do")) {
			forward=new ReplyAction().execute(request, response);
		}
		else if(action.equals("/replyDelete.do")) {
			forward=new ReplyDeleteAction().execute(request, response);
		}
		else if(action.equals("/login.do")) {
			forward=new LoginAction().execute(request, response);
		}
		else if(action.equals("/logout.do")) {
			forward=new LogoutAction().execute(request, response);
		}
		else if(action.equals("/join.do")) {
			forward=new JoinAction().execute(request, response);
		}

		else {
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}
		
		// 3) 사용자에게 결과 화면 출력
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}
		else {
			///// pageContext.forward("main.jsp");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request,response);
			// 디스패처
		}
		
	}
	
}

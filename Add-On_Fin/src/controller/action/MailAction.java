package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.users.UsersDAO;
import model.users.UsersVO;
import web.mail.MailSend;

public class MailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8"); 
		ActionForward forward = null;
      System.out.println("���� �׼� ����" + request.getParameter("id"));
      System.out.println("���� �׼� ����" + request.getParameter("mymail"));

      UsersDAO userDAO = new UsersDAO();
      UsersVO userVO = new UsersVO();
      String mid = request.getParameter("id");

      userVO.setId(mid);

      if (userDAO.getDBData(userVO) == null) {
         //PrintWriter out = response.getWriter();
         //out.println("<script>alert('�������� �ʴ� ���̵��Դϴ�.');history.go(-1);</script>");
      } else {
         MailSend sender = new MailSend();
         String mcontent = sender.MailSend((String) request.getParameter("mymail"));
         userVO.setPw(mcontent);
         // ������Ʈ
         userDAO.update(userVO);

         System.out.println(mcontent);

      }

      return forward;
   }

}
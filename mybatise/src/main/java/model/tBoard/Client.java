package model.tBoard;

import java.util.List;

public class Client {
	   public static void main(String[] args) {

	      TBoardDAO dao=new TBoardDAO();

	      TBoardVO vo=new TBoardVO();
	      vo.setTitle("������");
	      vo.setWriter("�ۼ��ھ�");
	      vo.setContent("���ֿ����");
	      
	      dao.insertTBoard(vo);

	      List<TBoardVO> datas=dao.getTBoardList(vo);
	      for(TBoardVO v:datas) {
	         System.out.println(v);
	      }
	      
	   }
	}
package model.tBoard;

import java.util.List;

public class Client {
	   public static void main(String[] args) {

	      TBoardDAO dao=new TBoardDAO();

	      TBoardVO vo=new TBoardVO();
	      vo.setTitle("제에목");
	      vo.setWriter("작성자아");
	      vo.setContent("내애요오옹");
	      
	      dao.insertTBoard(vo);

	      List<TBoardVO> datas=dao.getTBoardList(vo);
	      for(TBoardVO v:datas) {
	         System.out.println(v);
	      }
	      
	   }
	}
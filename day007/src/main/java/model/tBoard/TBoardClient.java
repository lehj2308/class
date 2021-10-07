package model.tBoard;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TBoardClient {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		TBoardService bs=(TBoardService)factory.getBean("tBoardService");
		
		TBoardVO vo=new TBoardVO();
		vo.setContent("내용 작성중~~");
		vo.setTitle("제목입니다!");
		vo.setWriter("관리자");
		bs.insertTBoard(vo);
		
		List<TBoardVO> datas=bs.getTBoardList(vo);
		for(TBoardVO data:datas) {
			System.out.println(data);
		}
		
		factory.close();
	}
}

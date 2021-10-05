package model.tBoard;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TBoardClient {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		TBoardService bs=(TBoardService)factory.getBean("tBoardService");
		
		TBoardVO vo=new TBoardVO();
		
		List<TBoardVO> datas=bs.getTBoardList(vo);
		for(TBoardVO data:datas) {
			System.out.println(data);
		}
		
		factory.close();
	}
}

package com.lee.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import model.tBoard.TBoardService;
import model.tBoard.TBoardVO;
import model.tMember.TMemberService;
import model.tMember.TMemberVO;

public class Client {
	public static void main(String[] args) {
		
		AbstractApplicationContext factory= new GenericXmlApplicationContext("applicationContext.xml");
		
		TMemberService ms=(TMemberService)factory.getBean("tMemberService");
		TMemberVO vo1=new TMemberVO();
		vo1.setId("admin");
		vo1.setPassword("1234");
		TMemberVO data=ms.getTMember(vo1);
		if(data!=null) {
			System.out.println("로그인 성공!");
		}
		else {
			System.out.println("로그인 실패!");
		}
		
		
		TBoardService bs=(TBoardService)factory.getBean("tBoardService");
		TBoardVO vo2=new TBoardVO();
		
		vo2.setContent("실습중!");
		vo2.setTitle("실습");
		vo2.setWriter("관리자");
		bs.insertTBoard(vo2);
		
		vo2=new TBoardVO();
		vo2.setTitle("제목");
		/*List<TBoardVO> datas=bs.getTBoardList(vo2);
		for(TBoardVO v:datas) {
			System.out.println(v);
		}*/
		
		factory.close();
	}
}

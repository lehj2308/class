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
			System.out.println("�α��� ����!");
		}
		else {
			System.out.println("�α��� ����!");
		}
		
		
		TBoardService bs=(TBoardService)factory.getBean("tBoardService");
		TBoardVO vo2=new TBoardVO();
		
		vo2.setContent("�ǽ���!");
		vo2.setTitle("�ǽ�");
		vo2.setWriter("������");
		bs.insertTBoard(vo2);
		
		vo2=new TBoardVO();
		vo2.setTitle("����");
		/*List<TBoardVO> datas=bs.getTBoardList(vo2);
		for(TBoardVO v:datas) {
			System.out.println(v);
		}*/
		
		factory.close();
	}
}

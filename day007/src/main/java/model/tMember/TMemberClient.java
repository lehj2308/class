package model.tMember;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TMemberClient {

	public static void main(String[] args) {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		TMemberService ms=(TMemberService)factory.getBean("tMemberService");
		
		TMemberVO vo=new TMemberVO();
		vo.setId("admin");
		vo.setPassword("1234");
		TMemberVO data=ms.getTMember(vo);

		if(data!=null) {
			// 로그인 성공
			System.out.println("로그인 성공!");
		}
		else {
			// 로그인 실패
			System.out.println("로그인 실패!");
		}

		factory.close();
	}
}

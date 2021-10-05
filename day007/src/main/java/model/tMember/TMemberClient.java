package model.tMember;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TMemberClient {

	public static void main(String[] args) {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		TMemberService bs = (TMemberService) factory.getBean("tMemberService");

		TMemberVO vo = new TMemberVO();

		List<TMemberVO> datas = bs.getTMemberList(vo);

		for (TMemberVO data : datas) {
			System.out.println(data);
		}

		factory.close();
	}
}

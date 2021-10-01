package test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client {
	
	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		Champ champ=(Champ)factory.getBean("champ");
		champ.attack();
		// champ.print();
		
		factory.close();
	}
}

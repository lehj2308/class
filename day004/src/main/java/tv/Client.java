package tv;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		

		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");

		TV tv=(TV)factory.getBean("tv");
		tv.turnOn();
		tv.channelUp();
		tv.volumeUp();

		factory.close();

		
	}
}

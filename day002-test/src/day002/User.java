package day002;

public class User {
	public static void main(String[] args) {
		BeanFactory factory=new BeanFactory();
		TV tv=(TV)factory.getBean(args[0]);
		tv.turnOn();
		tv.channelUp();
		tv.volumeUp();
		tv.volumeDown();
		tv.channelDown();
		tv.turnOff();
	}
}

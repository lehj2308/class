package test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		
		// 1. ������ �����̳ʸ� ���۽��Ѻ���!~~
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. ��ü�� ��ö�ϸ�, �ش� ��ü�� �ش�.
		// == Look up
		/*Test t=(Test)factory.getBean("test");
		t.print();*/
		Phone phone=(Phone)factory.getBean("phone");
		phone.power();
		phone.soundUp();
		
		
		// 3. ������ �����̳� ����
		factory.close();
		
		// => Console
		// ���� <bean> ��ϵ� ��� Ŭ������ ���� ��ü�� �̸� ����� �����ϴ�!
		// pre-loading ��� �ε�
		// �⺻������ ȣ���!
		
		// => Console
		// �⺻ ������ ȣ���!
		// ���
		
	}
}

package day002;

public class GaPhone implements Phone{

	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("���� ����");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("���� ����");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("�Ҹ� +=10");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("�Ҹ� -=10");
	}
}
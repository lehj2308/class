package day002;

public class GaPhone implements Phone{

	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("傈盔 难咙");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("傈盔 波咙");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("家府 +=10");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("家府 -=10");
	}
}
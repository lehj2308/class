package day002;

public class SaTV implements TV{

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("����ON");
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("ä��++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("����++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("����--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("ä��--");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("����OFF");
	}

}

package day002;

public class SaTV implements TV{

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("전원ON");
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("채널++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("볼륨++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("볼륨--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("채널--");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("전원OFF");
	}

}

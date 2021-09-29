package tv;

public class SaTV implements TV{

	private Remote rm;

	public SaTV() {
		System.out.println("LG티비 기본생성자 호출됨");
	}
	public SaTV(Remote rm) {
		this.rm=rm;
		System.out.println("삼성티비 생성자 호출됨");
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("전원ON");
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		// System.out.println("채널++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		// System.out.println("볼륨++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		// System.out.println("볼륨--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		// System.out.println("채널--");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("전원OFF");
	}

}

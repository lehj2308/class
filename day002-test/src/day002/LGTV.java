package day002;

public class LGTV implements TV{

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("전원 켜짐");
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("채널 위로 이동");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("볼륨 높이기");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("볼륨 낮추기");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("채널 아래로 이동");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("전원 꺼짐");
	}

}

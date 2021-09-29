package tv;

public class SaRemote implements Remote{

	public SaRemote() {
		System.out.println("삼성리모컨 기본생성자 호출됨");
	}
	
	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("삼성리모컨으로 채널++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("삼성리모컨으로 볼륨++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("삼성리모컨으로 볼륨--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("삼성리모컨으로 채널--");
	}
	
}

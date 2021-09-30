package tv;

public class LGRemote implements Remote{

	
	public LGRemote() {
		System.out.println("LG리모컨 기본생성자 호출됨");
	}
	
	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("LG리모컨으로 채널 위로 이동");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("LG리모컨으로 볼륨 높이기");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("LG리모컨으로 볼륨 낮추기");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("LG리모컨으로 채널 아래로 이동");
	}

	
}

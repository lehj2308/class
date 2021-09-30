package tv;

import java.util.Map;

public class LGTV implements TV{
	
	private Remote rm;
	private int num;
	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public void setRm(Remote rm) {
		this.rm = rm;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public LGTV() {
		System.out.println("LG티비 기본생성자 호출됨");
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("전원 켜짐 : " + num);
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		rm.channelUp();
		// System.out.println("채널 위로 이동");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		rm.volumeUp();
		// System.out.println("볼륨 높이기");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		rm.volumeDown();
		// System.out.println("볼륨 낮추기");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		rm.channelDown();
		// System.out.println("채널 아래로 이동");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub		
		System.out.println("전원 꺼짐");
	}

}

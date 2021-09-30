package tv;

import java.util.Map;

public class SaTV implements TV{

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
	public SaTV() {
		System.out.println("삼성티비 기본생성자 호출됨");
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("전원ON : " + num);
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		rm.channelUp();
		// System.out.println("채널++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		rm.volumeUp();
		// System.out.println("볼륨++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		rm.volumeDown();
		// System.out.println("볼륨--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		rm.channelDown();
		// System.out.println("채널--");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("전원OFF");
	}

}

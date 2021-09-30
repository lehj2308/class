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
		System.out.println("�ＺƼ�� �⺻������ ȣ���");
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("����ON : " + num);
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		rm.channelUp();
		// System.out.println("ä��++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		rm.volumeUp();
		// System.out.println("����++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		rm.volumeDown();
		// System.out.println("����--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		rm.channelDown();
		// System.out.println("ä��--");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("����OFF");
	}

}

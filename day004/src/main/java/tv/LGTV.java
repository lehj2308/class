package tv;

public class LGTV implements TV{
	
	private Remote rm;

	public LGTV() {
		System.out.println("LGƼ�� �⺻������ ȣ���");
	}
	public LGTV(Remote rm) {
		this.rm=rm;
		System.out.println("LGƼ�� ������ ȣ���");
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("���� ����");
	}

	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		rm.channelUp();
		// System.out.println("ä�� ���� �̵�");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		rm.volumeUp();
		// System.out.println("���� ���̱�");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		rm.volumeDown();
		// System.out.println("���� ���߱�");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		rm.channelDown();
		// System.out.println("ä�� �Ʒ��� �̵�");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub		
		System.out.println("���� ����");
	}

}

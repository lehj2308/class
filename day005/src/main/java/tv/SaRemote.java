package tv;

public class SaRemote implements Remote{

	public SaRemote() {
		System.out.println("�Ｚ������ �⺻������ ȣ���");
	}
	
	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("�Ｚ���������� ä��++");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("�Ｚ���������� ����++");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("�Ｚ���������� ����--");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("�Ｚ���������� ä��--");
	}
	
}

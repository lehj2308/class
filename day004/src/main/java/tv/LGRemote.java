package tv;

public class LGRemote implements Remote{

	
	public LGRemote() {
		System.out.println("LG������ �⺻������ ȣ���");
	}
	
	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		System.out.println("LG���������� ä�� ���� �̵�");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("LG���������� ���� ���̱�");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("LG���������� ���� ���߱�");
	}

	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		System.out.println("LG���������� ä�� �Ʒ��� �̵�");
	}

	
}

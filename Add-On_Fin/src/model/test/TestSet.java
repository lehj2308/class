package model.test;

import java.util.ArrayList;

public class TestSet { // �� +���
	private TestReplyVO reply; // ��� 1:
	private ArrayList<TestReplyVO> rrlist = new ArrayList<TestReplyVO>(); // ���� N

	public TestReplyVO getReply() {
		return reply;
	}

	public void setReply(TestReplyVO reply) {
		this.reply = reply;
	}

	public ArrayList<TestReplyVO> getRrlist() {
		return rrlist;
	}

	public void setRrlist(ArrayList<TestReplyVO> rrlist) {
		this.rrlist = rrlist;
	}

	@Override
	public String toString() {
		return "TestSet [reply=" + reply + ", rrlist=" + rrlist + "]";
	}

	
	
}

package model.test;

import java.util.ArrayList;

public class TestSet { // ´ñ +´ë´ñ
	private TestReplyVO reply; // ´ñ±Û 1:
	private ArrayList<TestReplyVO> rrlist = new ArrayList<TestReplyVO>(); // ´ë´ñ±Û N

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

package model.board;

import java.util.ArrayList;

public class BoardSet {
	
	// �Խñ� ��ü
	private BoardVO bVO;
	// ��� ����Ʈ
	private ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();
	// ���� ����Ʈ
	private ArrayList<ReplyVO> rrlist = new ArrayList<ReplyVO>();
	
	public BoardVO getbVO() {
		return bVO;
	}
	public void setbVO(BoardVO bVO) {
		this.bVO = bVO;
	}
	public ArrayList<ReplyVO> getRlist() {
		return rlist;
	}
	public void setRlist(ArrayList<ReplyVO> rlist) {
		this.rlist = rlist;
	}
	public ArrayList<ReplyVO> getRrlist() {
		return rrlist;
	}
	public void setRrlist(ArrayList<ReplyVO> rrlist) {
		this.rrlist = rrlist;
	}
	@Override
	public String toString() {
		return "BoardSet [bVO=" + bVO + ", ��� : " + rlist + ", ���� : " + rrlist + "]";
	}

}

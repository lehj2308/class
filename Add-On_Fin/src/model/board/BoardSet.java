package model.board;

import java.util.ArrayList;

public class BoardSet {
	
	// °Ô½Ã±Û °´Ã¼
	private BoardVO bVO;
	// ´ñ±Û ¸®½ºÆ®
	private ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();
	// ´ë´ñ±Û ¸®½ºÆ®
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
		return "BoardSet [bVO=" + bVO + ", ´ñ±Û : " + rlist + ", ´ë´ñ±Û : " + rrlist + "]";
	}

}

package model.message;

import java.util.Date;

public class MessageVO {
	private int mnum;
	private String unum;
	private String msg;
	private int favcount;
	private int replycount;
	private Date mdate;
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getUnum() {
		return unum;
	}
	public void setUnum(String unum) {
		this.unum = unum;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getFavcount() {
		return favcount;
	}
	public void setFavcount(int favcount) {
		this.favcount = favcount;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	@Override
	public String toString() {
		return "MessageVO [mnum=" + mnum + ", unum=" + unum + ", msg=" + msg + ", favcount=" + favcount + ", replycount="
				+ replycount + ", mdate=" + mdate + "]";
	}
}

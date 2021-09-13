package model.message;

import java.util.Date;

public class ReplyVO {
	private int rnum;
	private int mnum;
	private String unum;
	private Date rdate;
	private String rmsg;
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
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
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getRmsg() {
		return rmsg;
	}
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
	@Override
	public String toString() {
		return "ReplyVO [rnum=" + rnum + ", mnum=" + mnum + ", unum=" + unum + ", rdate=" + rdate + ", rmsg=" + rmsg + "]";
	}
}

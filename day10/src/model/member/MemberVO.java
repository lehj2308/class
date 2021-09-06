package model.member;

public class MemberVO {
	private int unum;
	private String userID;
	private String userPW;
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	@Override
	public String toString() {
		return "["+unum+","+userID+","+userPW+"]";
	}
	
	
}

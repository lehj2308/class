package model.member;

public class MemberVO {
	private int userNum;
	private String userID;
	private String userPW;
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
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
		return "MemberVO [userNum=" + userNum + ", userID=" + userID + ", userPW=" + userPW + "]";
	}
	
}

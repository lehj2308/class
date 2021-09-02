package model;

public class MemberBean {

	private String userID = "lee";
	private String userPW = "1234";
	public String getUserID() {
		return userID;
	}
	public String getUserPW() {
		return userPW;
	}
	
	public Boolean login(String userID, String userPW) {
		Boolean res=false;
		if(this.userID.equals(userID)) {
			if(this.userPW.equals(userPW)) {
				res=true;
			}
		}
		return res;
	}
}

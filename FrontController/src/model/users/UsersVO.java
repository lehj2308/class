package model.users;

import java.sql.Date;

public class UsersVO {
	private String unum;
	private String name;
	private String passwd;
	private Date udate;
	public String getUnum() {
		return unum;
	}
	public void setUnum(String unum) {
		this.unum = unum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	@Override
	public String toString() {
		return "UserVO [unum=" + unum + ", name=" + name + ", passwd=" + passwd + ", udate=" + udate + "]";
	}
	
	
}

package model.bBank;

public class BBankVO {
	private int bnum;
	private String bname;
	private int bbalance;

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getBbalance() {
		return bbalance;
	}

	public void setBbalance(int bbalance) {
		this.bbalance = bbalance;
	}

	@Override
	public String toString() {
		return "[ " + bnum + "¹ø " + bname + "´Ô ÀÜ¾×Àº" + bbalance + "¿ø ÀÔ´Ï´Ù. ]";
	}

}

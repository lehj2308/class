package model.aBank;

public class ABankVO {
	private int anum;
	private String aname;
	private int abalance;

	public int getAnum() {
		return anum;
	}

	public void setAnum(int anum) {
		this.anum = anum;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public int getAbalance() {
		return abalance;
	}

	public void setAbalance(int abalance) {
		this.abalance = abalance;
	}

	@Override
	public String toString() {
		return "[ " + anum + "¹ø " + aname + "´Ô ÀÜ¾×Àº" + abalance + "¿ø ÀÔ´Ï´Ù. ]";
	}

}

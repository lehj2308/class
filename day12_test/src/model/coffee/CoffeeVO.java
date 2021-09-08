package model.coffee;

public class CoffeeVO {
	private int num;
	private String name;
	private int price;
	private String information;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@Override
	public String toString() {
		return "[번호: "+num+" 제품명: "+name+" 가격: "+price+" 설명:"+information+"]";
	}
	
	
}

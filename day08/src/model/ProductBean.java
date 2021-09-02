package model;


public class ProductBean {
	
	private String[] product= {"모자", "상의", "하의"};
	private int[] price= {5000, 7000, 10000};
	public String[] getProduct() {
		return product;
	}
	public int[] getPrice() {
		return price;
	}
	
	public int calc(String name, int cnt) {
		int total=0;	// 사용자가 직접입력하지않는 데이터 -> 초기화 필수대상!
		
		for(int i=0;i<product.length;i++) {
			if(product[i].equals(name)){
				total=price[i]*cnt;
			}
		}
		
		return total;
	}
	
	
	
	
}

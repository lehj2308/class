package model;


public class ProductBean {
	
	private String[] product= {"����", "����", "����"};
	private int[] price= {5000, 7000, 10000};
	public String[] getProduct() {
		return product;
	}
	public int[] getPrice() {
		return price;
	}
	
	public int calc(String name, int cnt) {
		int total=0;	// ����ڰ� �����Է������ʴ� ������ -> �ʱ�ȭ �ʼ����!
		
		for(int i=0;i<product.length;i++) {
			if(product[i].equals(name)){
				total=price[i]*cnt;
			}
		}
		
		return total;
	}
	
	
	
	
}

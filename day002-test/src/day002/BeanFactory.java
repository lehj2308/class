package day002;

public class BeanFactory {
	public Object getBean(String beanName) {
		if(beanName.equals("LG")) {
			return new LGTV();
		}
		else if(beanName.equals("�Ｚ")) {
			return new SaTV();
		}
		
		return null;
	}
}

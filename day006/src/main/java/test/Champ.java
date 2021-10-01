package test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("champ")
public class Champ implements ChampAction {
	private String name;
	private List<String> inven;
	// @Autowired // 컨테이너는 자신이 만들어두었던 객체의 타입을 확인! 같은타입이 2개일경우나 아예 없을경우 에러
	@Resource(name="bow")
	private Weapon weapon;
	@Override
	public void attack() {
		weapon.attack();
		// 널포인터익셉션(예외) 발생우려가있으니, new 했어야했다!
		// -> 멤버변수로 처리, 생성자 주입
		// -> setter 주입으로 변경(기본 생성자가 필요!)
	}
	@Override
	public void print() {
		System.out.println(name);
		for(String v:inven) {
			System.out.println(v);
		}
	}
}

package test;

// <bean class="test.Sword" id="sword">
// @Component("sword")
public class Sword implements Weapon{

	@Override
	public void attack() {
		System.out.println("검으로 공격!");
	}

}

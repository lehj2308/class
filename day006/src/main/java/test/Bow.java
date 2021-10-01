package test;

import org.springframework.stereotype.Component;

public class Bow implements Weapon{

	@Override
	public void attack() {
		System.out.println("원거리 공격-!!");
	}

}

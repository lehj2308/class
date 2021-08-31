package day04;

import java.util.ArrayList;

public class SignupDAO {
	private ArrayList<SignupVO> members = new ArrayList();

	public ArrayList<SignupVO> getMembers() {
		return members;
	}
	
	public void addMembers(SignupVO vo) {
		members.add(vo);
	}
}

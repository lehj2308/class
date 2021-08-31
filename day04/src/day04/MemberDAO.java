package day04;

import java.util.ArrayList;

public class MemberDAO {
	ArrayList<MemberVO> datas=new ArrayList<MemberVO>();

	public ArrayList<MemberVO> getDatas() {
		return datas;
	}
	public void addMember(MemberVO vo) {
		datas.add(vo);
	}
}

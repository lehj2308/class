package model.tMember;

import java.util.List;

public interface TMemberService {
	
	void insertTMember(TMemberVO vo);
	void updateTMember(TMemberVO vo);
	void deleteTMember(TMemberVO vo);
	List<TMemberVO> getTMemberList(TMemberVO vo);
	TMemberVO getTMember(TMemberVO vo);
}

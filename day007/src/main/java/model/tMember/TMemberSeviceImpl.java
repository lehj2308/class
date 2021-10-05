package model.tMember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tMemberService")
public class TMemberSeviceImpl implements TMemberService{

	@Autowired
	private TMemberDAO tMemberDAO;
	
	@Override
	public void insertTMember(TMemberVO vo) {
		tMemberDAO.insertTMember(vo);
		
	}

	@Override
	public void updateTMember(TMemberVO vo) {
		tMemberDAO.updateTMember(vo);
	}

	@Override
	public void deleteTMember(TMemberVO vo) {
		tMemberDAO.deleteTMember(vo);
	}

	@Override
	public List<TMemberVO> getTMemberList(TMemberVO vo) {
		return tMemberDAO.getTMemberList(vo);
	}

	@Override
	public TMemberVO getTMember(TMemberVO vo) {
		return tMemberDAO.getTMember(vo);
	}

	
}

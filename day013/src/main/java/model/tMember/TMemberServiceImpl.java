package model.tMember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tMemberService")
public class TMemberServiceImpl implements TMemberService {

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
	public TMemberVO getTMember(TMemberVO vo) {
		try {
			return tMemberDAO.getTMember(vo);
		}catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<TMemberVO> getTMemberList(TMemberVO vo) {
		return tMemberDAO.getTMemberList(vo);
	}

}

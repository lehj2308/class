package model.tBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tBoardService")
public class TBoardServiceImpl implements TBoardService{

	@Autowired
	private TBoardDAO tBoardDAO;
	
	public TBoardServiceImpl() {
	}
	
	@Override
	public void insertTBoard(TBoardVO vo) {
		/*if(vo.getId()==0) {
	         throw new IllegalArgumentException("id PK : 0 �Ұ���!");
	         // ��Ÿ�� ����(����ÿ� �߻�,üũ�Ǵ� ����)
	      }*/
		tBoardDAO.insertTBoard(vo);
	}

	@Override
	public void updateTBoard(TBoardVO vo) {
		tBoardDAO.updateTBoard(vo);
	}

	@Override
	public void deleteTBoard(TBoardVO vo) {
		tBoardDAO.deleteTBoard(vo);
	}

	@Override
	public List<TBoardVO> getTBoardList(TBoardVO vo) {
		return tBoardDAO.getTBoardList(vo);
	}

	@Override
	public TBoardVO getTBoard(TBoardVO vo) {
		return tBoardDAO.getTBoard(vo);
	}

}

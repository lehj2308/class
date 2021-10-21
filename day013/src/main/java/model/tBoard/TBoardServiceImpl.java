package model.tBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tBoardService")
public class TBoardServiceImpl implements TBoardService{

	@Autowired
	private TBoardDAO tBoardDAO;
	
	@Override
	public void insertTBoard(TBoardVO vo) {
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
	public List<TBoardVO> getTBoardList(TBoardVO vo, int page) {
		return tBoardDAO.getTBoardList(vo, page);
	}

	@Override
	public TBoardVO getTBoard(TBoardVO vo) {
		return tBoardDAO.getTBoard(vo);
	}

}

package model.tBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.app.common.LogPlusAdvice;

@Service("tBoardService")
public class TBoardServiceImpl implements TBoardService{

	@Autowired
	private TBoardDAO tBoardDAO;
	private LogPlusAdvice logPlusAdvice;
	
	public TBoardServiceImpl() {
		this.logPlusAdvice=new LogPlusAdvice();
	}
	
	@Override
	public void insertTBoard(TBoardVO vo) {
		logPlusAdvice.printPlusLog();
		tBoardDAO.insertTBoard(vo);
	}

	@Override
	public void updateTBoard(TBoardVO vo) {
		logPlusAdvice.printPlusLog();
		tBoardDAO.updateTBoard(vo);
	}

	@Override
	public void deleteTBoard(TBoardVO vo) {
		logPlusAdvice.printPlusLog();
		tBoardDAO.deleteTBoard(vo);
	}

	@Override
	public List<TBoardVO> getTBoardList(TBoardVO vo) {
		logPlusAdvice.printPlusLog();
		return tBoardDAO.getTBoardList(vo);
	}

	@Override
	public TBoardVO getTBoard(TBoardVO vo) {
		logPlusAdvice.printPlusLog();
		return tBoardDAO.getTBoard(vo);
	}

}

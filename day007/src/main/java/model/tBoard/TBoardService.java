package model.tBoard;

import java.util.List;

public interface TBoardService {
	void insertTBoard(TBoardVO vo);
	void updateTBoard(TBoardVO vo);
	void deleteTBoard(TBoardVO vo);
	List<TBoardVO> getTBoardList(TBoardVO vo);
	TBoardVO getTBoard(TBoardVO vo);
}

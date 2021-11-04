package model.tBoard;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class JPATBoardDAO {

	@PersistenceContext
	private EntityManager em;

	public void insertTBoard(TBoardVO vo) {
		em.persist(vo);
	}

	public void updateTBoard(TBoardVO vo) {
		em.merge(vo);
	}

	public void deleteTBoard(TBoardVO vo) {
		em.remove(em.find(TBoardVO.class, vo.getId()));
	}

	public TBoardVO getTBoard(TBoardVO vo) {
		return (TBoardVO)em.find(TBoardVO.class, vo.getId());
	}

	public List<TBoardVO> getTBoardList(TBoardVO vo) {
		return em.createQuery("select b from TBoard b",TBoardVO );
	}

}

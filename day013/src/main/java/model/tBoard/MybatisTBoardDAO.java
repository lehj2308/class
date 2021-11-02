package model.tBoard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTBoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertTBoard(TBoardVO vo) {
		mybatis.insert("dao.insetTBoard", vo);
	}
	public void updateTBoard(TBoardVO vo) {
		mybatis.update("dao.updateTBoard", vo);
	}
	public void deleteTBoard(TBoardVO vo) {
		mybatis.delete("dao.deleteTBoard", vo);
	}
	public TBoardVO getTBoard(TBoardVO vo) {
		return (TBoardVO)mybatis.selectOne("dao.getTBoard", vo);
	}
	public List<TBoardVO> getTBoardList(TBoardVO vo){
	      return mybatis.selectList("dao.getTBoardList",vo);
	   }
	
}

/*package model.tBoard;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTBoardDAO extends SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertTBoard(TBoardVO vo) {
		getSqlSession().insert("dao.insetTBoard", vo);
	}
	public void updateTBoard(TBoardVO vo) {
		getSqlSession().update("dao.updateTBoard", vo);
	}
	public void deleteTBoard(TBoardVO vo) {
		getSqlSession().delete("dao.deleteTBoard", vo);
	}
	public TBoardVO getTBoard(TBoardVO vo) {
		return (TBoardVO)getSqlSession().selectOne("dao.getTBoard", vo);
	}
	public List<TBoardVO> getTBoardList(TBoardVO vo){
	      return getSqlSession().selectList("dao.getTBoardList",vo);
	   }
	
}
*/
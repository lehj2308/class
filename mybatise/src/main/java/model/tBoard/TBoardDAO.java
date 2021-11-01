package model.tBoard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class TBoardDAO {
   private SqlSession mybatis;
   public TBoardDAO(){
      mybatis=SqlSessionFactoryBean.getSqlSessionInstance();
   }
   public void insertTBoard(TBoardVO vo) {
      mybatis.insert("dao.insertTBoard", vo);
      mybatis.commit();
   }
   public void updateTBoard(TBoardVO vo) {
      mybatis.update("dao.updateTBoard",vo);
      mybatis.commit();
   }
   public void deleteTBoard(TBoardVO vo) {
      mybatis.delete("dao.deleteTBoard",vo);
      mybatis.commit();
   }
   public TBoardVO getTBoard(TBoardVO vo) {
      return (TBoardVO) mybatis.selectOne("dao.getTBoard",vo);
   }
   public List<TBoardVO> getTBoardList(TBoardVO vo) {
      return mybatis.selectList("dao.getTBoardList",vo);
   }
}
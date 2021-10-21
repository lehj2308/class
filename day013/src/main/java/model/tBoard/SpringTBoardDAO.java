package model.tBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SpringTBoardDAO extends JdbcDaoSupport {

	private final String insertSQL = "insert into tboard (id,title,writer,content) values((select nvl(max(id),0)+1 from tboard),?,?,?)";
	private final String updateSQL="update tBoard set title=?, content=? where id=?";
	private final String deleteSQL="delete from tBoard where id=?";
	private final String getTBoardSQL="select * from tBoard where id=?";
	private final String getTBoardListSQL = "select * from tBoard where title LIKE '%'||?||'%' order by id desc";

	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	public void insertTBoard(TBoardVO vo) {
		System.out.println("jdbcTemplate으로 insert");
		getJdbcTemplate().update(insertSQL, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	public void updateTBoard(TBoardVO vo) {
		System.out.println("jdbcTemplate으로 update");
		getJdbcTemplate().update(updateSQL, vo.getTitle(), vo.getContent(), vo.getId());
	}
	public void deleteTBoard(TBoardVO vo) {
		System.out.println("jdbcTemplate으로 delete");
		getJdbcTemplate().update(deleteSQL, vo.getId());
	}
	public List<TBoardVO> getTBoardList(TBoardVO vo) {
		System.out.println("jdbcTemplate으로 getTBoardList");
		Object[] args = { vo.getTitle() };
		return getJdbcTemplate().query(getTBoardListSQL, args, new TBoardRowMapper());
	}
	public TBoardVO getTBoard(TBoardVO vo) {
	      System.out.println("jdbcTemplate으로 getBoard");
	      Object[] args= { vo.getId() };
	      return getJdbcTemplate().queryForObject(getTBoardSQL,args,new TBoardRowMapper());
	   }
}

class TBoardRowMapper implements RowMapper<TBoardVO> {

	@Override
	public TBoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TBoardVO data = new TBoardVO();
		data.setId(rs.getInt("id"));
		data.setTitle(rs.getString("title"));
		data.setWriter(rs.getString("writer"));
		data.setContent(rs.getString("content"));
		data.setWdate(rs.getDate("wdate"));
		return data;
	}

}
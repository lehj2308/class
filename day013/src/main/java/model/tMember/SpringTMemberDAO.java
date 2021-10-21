package model.tMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("springTMmberDAO")
public class SpringTMemberDAO extends JdbcDaoSupport {

	private final String insertSQL = "insert into tMember (id,password,name,role) values(?,?,?,'USER')";
	private static String updateSQL = "update tMember set password=?, name=?, role=? where id=?";
	private static String deleteSQL = "delete from tMember where id=?";
	private final String getTMemberSQL = "select * from tMember where id=? and password=?";
	private static String getTMemberListSQL = "select * from tMember";

	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	public void insertTMember(TMemberVO vo) {
		System.out.println("jdbcTemplate insert");
		getJdbcTemplate().update(insertSQL, vo.getId(), vo.getPassword(), vo.getName());
	}
	
	public void updateTMember(TMemberVO vo) {
		System.out.println("jdbcTemplate update");
		getJdbcTemplate().update(updateSQL, vo.getPassword(), vo.getName(), vo.getRole(), vo.getId());
	}

	public void deleteTMember(TMemberVO vo) {
		System.out.println("jdbcTemplate delete");
		getJdbcTemplate().update(deleteSQL, vo.getId());
	}

	public TMemberVO getTMember(TMemberVO vo) {
		System.out.println("jdbcTemplate getTMember");
		Object[] args = { vo.getId(), vo.getPassword() };
		return getJdbcTemplate().queryForObject(getTMemberSQL, args, new TMemberRowMapper());
	}
	
	public List<TMemberVO> getTMemberList(TMemberVO vo) {
		System.out.println("jdbcTemplate getTMemberList");
		return getJdbcTemplate().query(getTMemberListSQL, new TMemberRowMapper());
	}

}

class TMemberRowMapper implements RowMapper<TMemberVO> {

	@Override
	public TMemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TMemberVO data = new TMemberVO();
		data.setId(rs.getString("id"));
		data.setPassword(rs.getString("password"));
		data.setName(rs.getString("name"));
		data.setRole(rs.getString("role"));
		return data;
	}

}
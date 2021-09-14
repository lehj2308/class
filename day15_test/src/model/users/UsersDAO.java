package model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class UsersDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public UsersVO login(UsersVO vo) {
		UsersVO data = new UsersVO();
		conn = JNDI.getConnection();
		String sql;

		try {
			sql = "select * from users where unum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUnum());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("passwd").equals(vo.getPasswd())) {
					data.setName(rs.getString("name"));
					data.setPasswd(rs.getString("passwd"));
					data.setUnum(rs.getString("unum"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public Boolean join(UsersVO vo) {
		Boolean res = false;
		conn = JNDI.getConnection();
		String sql;

		try {
			sql = "select * from users where unum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				sql="insert into users values(?,?,?,sysdate)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getUnum());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getPasswd());
				pstmt.executeUpdate();
				res=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}
}

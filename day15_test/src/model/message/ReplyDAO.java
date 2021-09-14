package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class ReplyDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public Boolean replyInsert(ReplyVO vo) {
		Boolean res = false;
		conn = JNDI.getConnection();
		String sql;

		try {
			sql = "insert into reply values((select nvl(max(rnum),0)+1 from reply),?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			pstmt.setString(2, vo.getUnum());
			pstmt.setString(3, vo.getRmsg());
			pstmt.executeUpdate();
			
			sql = "update message set replycount = replycount+1 where mnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			pstmt.executeUpdate();
			
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}
}
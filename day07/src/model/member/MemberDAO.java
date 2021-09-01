package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.JDBC;
import model.message.MessageVO;

public class MemberDAO {

	public Boolean loginDB(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res = false;
		try {
			String sql = "select userPW from member where userID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("userPW").equals(vo.getUserPW())) {
					res = true;
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("loginDB()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public Boolean signupDB(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			String sql = "insert into member values((select nvl(max(userNum),0)+1 from member),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			pstmt.setString(2, vo.getUserPW());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("signupDB()에서 출력");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
}

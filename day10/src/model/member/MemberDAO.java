package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.JDBC;

public class MemberDAO {
	public MemberVO login(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		MemberVO data = null;

		try {
			String sql = "select * from member where userID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("userPW").equals(vo.getUserPW())) {
					data = new MemberVO();
					data.setUnum(rs.getInt("unum"));
					data.setUserID(rs.getString("userID"));
					data.setUserPW(rs.getString("userPW"));
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("login 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	

	public Boolean signup(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res = false;
		System.out.println(vo);
		try {
			String sql = "insert into member values((select nvl(max(unum),0)+1 from member),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			pstmt.setString(2, vo.getUserPW());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("signup 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}

	public Boolean userUpdate(MemberVO vo, String newPW) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res = false;
		System.out.println(vo);
		System.out.println(newPW);
		try {
			String sql = "update member set userPW=? where unum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPW);
			pstmt.setInt(2, vo.getUnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("signup 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}

	public Boolean userDelete(MemberVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res = false;
		System.out.println(vo);
		try {
			String sql = "delete from member where unum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getUnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("userDelete 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
}

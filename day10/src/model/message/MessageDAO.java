package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

public class MessageDAO {

	public ArrayList<MessageVO> getList() {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<MessageVO> datas = new ArrayList();
		try {
			String sql = "select * from message order by mnum desc";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setMnum(rs.getInt("mnum"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getDate("wdate"));
				datas.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("getList 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}

	public ArrayList<MessageVO> searchList(String writer) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		ArrayList<MessageVO> datas = new ArrayList();
		try {
			String sql = "select * from message where writer=? order by mnum desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setMnum(rs.getInt("mnum"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getDate("wdate"));
				datas.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("searchList 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	public MessageVO content(MessageVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		MessageVO data=null;
		System.out.println(vo);
		try {
			String sql = "select * from message where mnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data=new MessageVO();
				data.setMnum(rs.getInt("mnum"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setWriter(rs.getString("writer"));
				data.setWdate(rs.getDate("wdate"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("content 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	public Boolean insertDB(MessageVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res =false;
		System.out.println(vo);
		try {
			String sql = "insert into message values((select nvl(max(mnum),0)+1 from message), ?, ?, ?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("insertDB 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public Boolean updateDB(MessageVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res =false;
		System.out.println(vo);
		try {
			String sql = "update message set title=?, content=?, writer=?, wdate=sysdate where mnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getMnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("insertDB 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public Boolean deleteDB(MessageVO vo) {
		Connection conn = JDBC.connect();
		PreparedStatement pstmt = null;
		Boolean res =false;
		System.out.println(vo);
		try {
			String sql = "delete from message where mnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("deleteDB 惯积");
			e.printStackTrace();
		} finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}

}

package model.tBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.common.JDBC;


// DAO -> 드라이버, CP, MyBatis, JPA, ...
// Service를 DAO 객체를 이용하여 CRUD 기능을 제공할 예정!
// 폰Service <-> 시계DAO
@Repository("tBoardDAO")
public class TBoardDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private final String insertSQL="insert into tBoard (id,title,writer,content) values((select nvl(max(id),0)+1 from tBoard),?,?,?)";
	private final String updateSQL="update tBoard set title=?, content=? where id=?";
	private final String deleteSQL="delete from tBoard where id=?";
	private final String getTBoardSQL="select * from tBoard where id=?";
	private final String getTBoardListSQL="select * from tBoard";
	
	
	public void insertTBoard(TBoardVO vo) {
		System.out.println("insertTBoard 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		try {
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("insertTBoard 오류");
		} finally {
			JDBC.close(conn, pstmt);
		}
	}

	public void updateTBoard(TBoardVO vo) {
		System.out.println("updateTBoard 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		try {
			pstmt=conn.prepareStatement(updateSQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("updateTBoard 오류");
		} finally {
			JDBC.close(conn, pstmt);
		}
	}

	public void deleteTBoard(TBoardVO vo) {
		System.out.println("deleteTBoard 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		try {
			pstmt=conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("deleteTBoard 오류");
		} finally {
			JDBC.close(conn, pstmt);
		}
	}

	public List<TBoardVO> getTBoardList(TBoardVO vo) {
		System.out.println("getTBoardList 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		rs=null;
		List<TBoardVO> datas=null;
		TBoardVO data=null;
		try {
			pstmt=conn.prepareStatement(getTBoardListSQL);
			rs=pstmt.executeQuery();
			datas=new ArrayList<TBoardVO>();
			while(rs.next()) {
				data=new TBoardVO();
				data.setId(rs.getInt("id"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));
				data.setContent(rs.getString("content"));
				data.setWdate(rs.getDate("wdate"));
				datas.add(data);
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("getTBoardList 오류");
		} finally {
			JDBC.close(conn, pstmt, rs);
		}
		return datas;
	}

	public TBoardVO getTBoard(TBoardVO vo) {
		System.out.println("getTBoard 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		rs=null;
		TBoardVO data=null;
		try {
			pstmt=conn.prepareStatement(getTBoardSQL);
			pstmt.setInt(1, vo.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new TBoardVO();
				data.setId(rs.getInt("id"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));
				data.setContent(rs.getString("content"));
				data.setWdate(rs.getDate("wdate"));
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("getTBoard 오류");
		} finally {
			JDBC.close(conn, pstmt, rs);
		}
		return data;
	}
}

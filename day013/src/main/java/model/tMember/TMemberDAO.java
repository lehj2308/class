package model.tMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.common.JDBC;

@Repository("tMemberDAO")
public class TMemberDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	private final String insertSQL = "insert into tMember (id,password,name,role) values(?,?,?,'USER')";
	private static String updateSQL="update tMember set password=?, name=?, role=? where id=?";
	private static String deleteSQL="delete from tMember where id=?";
	private final String getTMemberSQL = "select * from tMember where id=? and password=?";
	private static String getTMemberListSQL="select * from tMember";
	
	public boolean insertTMember(TMemberVO vo) {
		System.out.println("insertTMember 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		rs=null;
		boolean res=false;
		try {
			pstmt=conn.prepareStatement("select * from tMember where id=?");
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				pstmt=conn.prepareStatement(insertSQL);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getName());
				pstmt.executeUpdate();
				res=true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("insertTMember 오류");
		} finally {
			JDBC.close(conn, pstmt, rs);
		}
		return res;
	}
	
	public void updateTMember(TMemberVO vo) {
		System.out.println("updateTMember 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		try {
			pstmt=conn.prepareStatement(updateSQL);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getRole());
			pstmt.setString(4, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("updateTMember 오류");
		} finally {
			JDBC.close(conn, pstmt);
		}
	}
	
	public void deleteTMember(TMemberVO vo) {
		System.out.println("deleteTMember 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		try {
			pstmt=conn.prepareStatement(deleteSQL);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("deleteTMember 오류");
		} finally {
			JDBC.close(conn, pstmt);
		}
	}
	public TMemberVO getTMember(TMemberVO vo) {
		System.out.println("getTMember 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		rs=null;
		TMemberVO data=null;
		try {
			pstmt=conn.prepareStatement(getTMemberSQL);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new TMemberVO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setPassword(rs.getString("password"));
				data.setRole(rs.getString("role"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getTMember 오류");
		} finally {
			JDBC.close(conn, pstmt, rs);
		}
		return data;
	}
	
	public List<TMemberVO> getTMemberList(TMemberVO vo) {
		System.out.println("getTMemberList 실행");
		conn=JDBC.getConnection();
		pstmt=null;
		rs=null;
		List<TMemberVO> datas=null;
		TMemberVO data=null;
		try {
			pstmt=conn.prepareStatement(getTMemberListSQL);
			rs=pstmt.executeQuery();
			datas=new ArrayList<TMemberVO>();
			while(rs.next()) {
				data=new TMemberVO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setPassword(rs.getString("password"));
				data.setRole(rs.getString("role"));
				datas.add(data);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getTMemberList 오류");
		} finally {
			JDBC.close(conn, pstmt, rs);
		}
		return datas;
	}
	
}

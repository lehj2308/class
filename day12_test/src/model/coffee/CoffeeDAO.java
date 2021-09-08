package model.coffee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.DBCP;

public class CoffeeDAO {

	public ArrayList<CoffeeVO> selectAll() {
		Connection conn = DBCP.connect();
		PreparedStatement pstmt = null;
		ArrayList<CoffeeVO> datas = new ArrayList();
		try {
			String sql = "select * from coffee";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CoffeeVO data = new CoffeeVO();
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setPrice(rs.getInt("price"));
				datas.add(data);
			}
			rs.close();
			
		} catch (Exception e) {
			System.out.println("selecAll 惯积");
			e.printStackTrace();
		} finally {
			DBCP.disconnect(pstmt, conn);
		}
		return datas;
	}

	public CoffeeVO selectOne(CoffeeVO vo) {
		System.out.println("selectOne vo:" + vo);
		Connection conn = DBCP.connect();
		PreparedStatement pstmt = null;
		CoffeeVO data = null;
		try {
			String sql = "select * from coffee where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new CoffeeVO();
				data.setNum(rs.getInt("num"));
				data.setName(rs.getString("name"));
				data.setPrice(rs.getInt("price"));
				data.setInformation(rs.getString("information"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("selectOne 惯积");
			e.printStackTrace();
		} finally {
			DBCP.disconnect(pstmt, conn);
		}
		return data;
	}

	public Boolean insert(CoffeeVO vo) {
		System.out.println("insert vo:" + vo);
		Connection conn = DBCP.connect();
		PreparedStatement pstmt = null;
		Boolean res = false;
		try {
			String sql = "insert into coffee values((select nvl(max(num),0)+1 from coffee),?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getInformation());
			pstmt.executeUpdate();
			res = true;
		} catch (Exception e) {
			System.out.println("insert 惯积");
			e.printStackTrace();
		}finally {
			DBCP.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public Boolean update(CoffeeVO vo) {
		System.out.println("update vo:" + vo);
		Connection conn = DBCP.connect();
		PreparedStatement pstmt = null;
		Boolean res=false;
		try {
			String sql="update coffee set name=?,price=?,information=? where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getInformation());
			pstmt.setInt(4, vo.getNum());
			pstmt.executeUpdate();
			res=true;
			
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("update 惯积");
			e.printStackTrace();
		} finally {
			DBCP.disconnect(pstmt, conn);
		}
		return res;
	}
	
	public Boolean delete(CoffeeVO vo) {
		System.out.println("delete vo:" + vo);
		Connection conn = DBCP.connect();
		PreparedStatement pstmt=null;
		Boolean res=false;
		try {
			String sql="delete from coffee where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
			res=true;
		}catch(Exception e) {
			System.out.println("delete 惯积");
			e.printStackTrace();
		} finally {
			DBCP.disconnect(pstmt, conn);
		}
		return res;
	}
}

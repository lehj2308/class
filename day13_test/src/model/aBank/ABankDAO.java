package model.aBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.DBCP;

public class ABankDAO {

	public ABankVO getABank() {
		Connection conn=DBCP.connect();
		PreparedStatement pstmt=null;
		ABankVO data=new ABankVO();
		try {
			String sql="select * from aBank";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data.setAnum(rs.getInt("anum"));
				data.setAname(rs.getString("aname"));
				data.setAbalance(rs.getInt("abalance"));
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("getABank 발생");
			e.printStackTrace();
		}finally {
			DBCP.disconnect(pstmt, conn);
		}
		return data;
	}
	
	public Boolean aTrans(int bal) {
		Connection conn=DBCP.connect();
		PreparedStatement pstmt=null;
		Boolean res = false;
		try {
			conn.setAutoCommit(false);
			String sqlA="update aBank set abalance=abalance-? where anum=1001";
			String sqlB="update bBank set bbalance=bbalance+? where bnum=2001";
			pstmt=conn.prepareStatement(sqlA);
			pstmt.setInt(1, bal);
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement(sqlB);
			pstmt.setInt(1, bal);
			pstmt.executeUpdate();
			
			String sql="select abalance from aBank where anum=1001";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			if(rs.getInt(1)<0) {
				conn.rollback();
			}
			else {
				conn.commit(); // commit;
				res=true;
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("aTrans 발생");
			e.printStackTrace();
		}finally {
			DBCP.disconnect(pstmt, conn);
		}
		return res;
	}
	
}

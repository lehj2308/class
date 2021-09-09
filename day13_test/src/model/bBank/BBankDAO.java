package model.bBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.DBCP;

public class BBankDAO {

	public BBankVO getBBank() {
		Connection conn=DBCP.connect();
		PreparedStatement pstmt=null;
		BBankVO data=new BBankVO();
		try {
			String sql="select * from bBank";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data.setBnum(rs.getInt("bnum"));
				data.setBname(rs.getString("bname"));
				data.setBbalance(rs.getInt("bbalance"));
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
	
	public Boolean bTrans(int bal) {
		Connection conn=DBCP.connect();
		PreparedStatement pstmt=null;
		Boolean res = false;
		try {
			conn.setAutoCommit(false);
			String sqlB="update bBank set bbalance=bbalance-? where bnum=2001";
			String sqlA="update aBank set abalance=abalance+? where anum=1001";
			pstmt=conn.prepareStatement(sqlB);
			pstmt.setInt(1, bal);
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement(sqlA);
			pstmt.setInt(1, bal);
			pstmt.executeUpdate();
			
			String sql="select bbalance from bBank where bnum=2001";
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
			System.out.println("bTrans 발생");
			e.printStackTrace();
		}finally {
			DBCP.disconnect(pstmt, conn);
		}
		return res;
	}
}

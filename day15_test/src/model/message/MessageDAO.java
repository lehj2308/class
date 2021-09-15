package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class MessageDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ArrayList<MessageSet> selectAll(String unum, int cnt) {
		ArrayList<MessageSet> datas = new ArrayList<MessageSet>();
		conn = JNDI.getConnection();
		String sql;
		try {
			// 전체
			if ((unum == null) || (unum.equals(""))) {
				sql = "select * from (select * from message order by mdate desc) where rownum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnt);
			}
			// 특정 회원
			else {
				sql = "select * from (select * from message where unum=? order by mdate desc) where rownum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, unum);
				pstmt.setInt(2, cnt);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MessageSet ms = new MessageSet();
				MessageVO m = new MessageVO();
				ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();

				m.setMnum(rs.getInt("mnum"));
				m.setMsg(rs.getString("msg"));
				m.setFavcount(rs.getInt("favcount"));
				m.setUnum(rs.getString("unum"));
				m.setMdate(rs.getDate("mdate"));

				String rsql = "select * from (select * from reply where mnum=? order by rdate desc) where rownum <= 3";
				pstmt = conn.prepareStatement(rsql);
				pstmt.setInt(1, rs.getInt("mnum"));
				ResultSet rrs = pstmt.executeQuery();
				int rcnt = 0;
				while (rrs.next()) {
					ReplyVO r = new ReplyVO();
					r.setRdate(rrs.getDate("rdate"));
					r.setMnum(rrs.getInt("mnum"));
					r.setRnum(rrs.getInt("rnum"));
					r.setRmsg(rrs.getString("rmsg"));
					r.setUnum(rrs.getString("unum"));
					rlist.add(r);
					rcnt++;
				}
				m.setReplycount(rcnt);

				ms.setM(m);
				ms.setRlist(rlist);
				System.out.println(ms);
				datas.add(ms);
				rrs.close();
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
		return datas;
	}
	
	public Boolean messageInsert(MessageVO vo) {
		Boolean res = false;
		conn = JNDI.getConnection();
		String sql;

		try {
			sql = "insert into message(mnum,unum,msg,mdate) values((select nvl(max(mnum),0)+1 from message),?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUnum());
			pstmt.setString(2, vo.getMsg());
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
	
	public Boolean messageDelete(MessageVO vo) {
		Boolean res=false;
		conn = JNDI.getConnection();
		String sql;
		
		try {
			sql="delete from reply where mnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			pstmt.executeUpdate();
			sql="delete from message where mnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			pstmt.executeUpdate();
			
			res=true;
		}catch (SQLException e) {
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

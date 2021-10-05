package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

public class ReplyDAO {

	// ��� ���� �ۼ� 
	// �Է� �� : (R_ID, USER_NUM, B_ID, R_CONTENT, R_WRITER, PARENT_ID) 
	// DEFAULT �ڵ� �Է°�(R_DATE, DELETE_AT)
	static String sql_INSERT = "INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) "
			+ "VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),?,?,?,?,?)";
	// ��� ����
	static String sql_DELETE = "UPDATE BOARD_REPLY SET DELETE_AT='Y' WHERE R_ID =?";
	// ��� ������Ʈ 
	static String sql_UPDATE = "UPDATE BOARD_REPLY SET R_CONTENT=? WHERE R_ID=?";
	// ���� ID �� ��� ��ü���
	static String sql_SELECT_ALL_USER = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END AS R_CONTENT , "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM ("
			+ "SELECT * FROM BOARD_REPLY WHERE USER_NUM=? AND DELETE_AT='N' ORDER BY R_DATE DESC"
			+ ") BOARD_REPLY WHERE ROWNUM<=? "
			+ ") WHERE RNUM > ? ORDER BY R_DATE DESC";

	// ��� ��ü���
	static String sql_SELECT_ALL = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END AS R_CONTENT , "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM ("
			+ "SELECT * FROM BOARD_REPLY WHERE B_ID=? AND PARENT_ID=0 ORDER BY R_DATE DESC"
			+ ") BOARD_REPLY WHERE ROWNUM<=? "
			+ ") WHERE RNUM > ? ORDER BY R_DATE DESC";

	// ��� �ϳ� ���
	static String sql_SELECT_ONE = "SELECT R_ID, B_ID, USER_NUM, "
			+ "CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END AS R_WRITER, "
			+ "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END AS R_CONTENT, "
			+ "R_DATE, DELETE_AT, PARENT_ID FROM BOARD_REPLY WHERE R_ID=?";

	//========================================================================================
	// selectAll ��� ��ü + ���� List�� ����ִ� datas ��ȯ

	static int pageSize = 3; // ������ 10���� ��� ��°��� �ٲٽǶ� ���� �ٲٽø� �˴ϴ�.

	// userNum �� ���� �� 0�־��ּ���
	@SuppressWarnings("resource")
	public ArrayList<ReplySet> getDBList(ReplyVO vo, int pageNum) {
	

		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<ReplySet> datas = new ArrayList<ReplySet>();
		ReplyVO rvo = null;
		int cnt = 0;

		//System.out.println(uvo);
		//System.out.println(vo);
		//System.out.println(pageNum);

		int startNum = pageNum*pageSize;
		int lastNum = (pageNum*pageSize)+pageSize;

		try {
			// ��ü ���
			pstmt = conn.prepareStatement(sql_SELECT_ALL);
			pstmt.setInt(1, vo.getbId());
			pstmt.setInt(2, lastNum);
			pstmt.setInt(3, startNum);
			// System.out.println("��ü��� ���");

			System.out.println("start : "+lastNum);
			System.out.println("last : "+startNum);

			ResultSet rs = pstmt.executeQuery();
			// ��� ��ü

			while(rs.next()) {
				// System.out.println("replyDAO.getDBList while�ȿ��� ���");
				rvo = new ReplyVO();

				rvo.setrId(rs.getInt("R_ID"));            // ��� PK
				rvo.setbId(rs.getInt("B_ID"));            // �Խñ� ��ȣ
				rvo.setUserNum(rs.getInt("USER_NUM"));      // ���� ��ȣ
				rvo.setrWriter(rs.getString("R_WRITER"));   // �ۼ���
				rvo.setrContent(rs.getString("R_CONTENT"));   // ��� ����
				rvo.setrDate(rs.getDate("R_DATE"));         // �ۼ���
				rvo.setDeleteAt(rs.getString("DELETE_AT"));   // ��������
				rvo.setParentId(rs.getInt("PARENT_ID"));   // ��� ���� ����

				// System.out.println("��� 1��ü : "+rvo);

				String rrsql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=? ORDER BY R_DATE DESC";
				pstmt = conn.prepareStatement(rrsql);
				pstmt.setInt(1, rvo.getrId()); // ������ parent�� ����� ID
				ResultSet rrs = pstmt.executeQuery();

				ArrayList<ReplyVO> rrlist = new ArrayList<ReplyVO>();

				while(rrs.next()) {
					ReplyVO rrvo = new ReplyVO();

					rrvo.setrId(rrs.getInt("R_ID"));
					rrvo.setbId(rrs.getInt("B_ID"));
					rrvo.setUserNum(rrs.getInt("USER_NUM"));
					rrvo.setrContent(rrs.getString("R_CONTENT"));
					rrvo.setrDate(rrs.getDate("R_DATE"));
					rrvo.setDeleteAt(rrs.getString("DELETE_AT"));
					rrvo.setrWriter(rrs.getString("R_WRITER"));
					rrvo.setParentId(rrs.getInt("PARENT_ID"));

					// System.out.println("���� writer Ȯ�� : "+rrvo.getrWriter());
					rrlist.add(rrvo);

					// System.out.println("���� list"+rrlist);
				}
				rrs.close();

				ReplySet replySet = new ReplySet();
				replySet.setRvo(rvo); // ��� 1
				replySet.setRrlist(rrlist); // ��ۿ� ���� ����
				datas.add(replySet); // ���1 + ���� N��

				// System.out.println("replySet : "+replySet);
			}
			rs.close();

			String sql;

			// ��� ��ü ���� ���
			sql = "SELECT COUNT(*) FROM BOARD_REPLY WHERE B_ID=? AND DELETE_AT='N' AND PARENT_ID=0";  //  AND DELETE_AT='N' AND PARENT_ID=0 �̺κ� �߰�����!
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbId());

			ReplySet replySet = new ReplySet();

			ResultSet crs = pstmt.executeQuery();
			if(crs.next()) {
				cnt=crs.getInt(1);
			}
			crs.close();

			replySet.setReplyCnt(cnt);
			datas.add(replySet);

		}  catch(SQLException e) {
			System.out.println("ReplyDAO getDBList���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}

		/*for(ReplySet v : datas) {
	         System.out.println("���� : "+v);
	      }*/
		return datas;
	}
	//========================================================================================	
	@SuppressWarnings("resource")
	public MyReplySet myReply(UsersVO uvo, int pageNum) {
		System.out.println("myReply() ����");
		System.out.println("userNum : "+ uvo.getUserNum());
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();
		MyReplySet datas = new MyReplySet();
		// �� ��ü ����
		int cnt = 0;
		String sql;

		int startNum = pageNum*pageSize;
		int lastNum = (pageNum*pageSize)+pageSize;

		System.out.println(startNum+"     "+lastNum);

		try {

			pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
			pstmt.setInt(1, uvo.getUserNum());
			pstmt.setInt(2, lastNum);
			pstmt.setInt(3, startNum);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				System.out.println("���Ϲ� ����");
				ReplyVO rvo = new ReplyVO();

				rvo.setrId(rs.getInt("R_ID"));
				rvo.setbId(rs.getInt("B_ID"));
				rvo.setUserNum(rs.getInt("USER_NUM"));
				rvo.setrContent(rs.getString("R_CONTENT"));
				rvo.setrDate(rs.getDate("R_DATE"));
				rvo.setDeleteAt(rs.getString("DELETE_AT"));
				rvo.setrWriter(rs.getString("R_WRITER"));
				rvo.setParentId(rs.getInt("PARENT_ID"));

				rlist.add(rvo);

				System.out.println("rvo : "+rvo);
			}
			rs.close();
			System.out.println("���Ϲ� ���");
			System.out.println("rlist : "+rlist);

			sql = "SELECT COUNT(*) FROM BOARD_REPLY WHERE USER_NUM=? AND DELETE_AT='N'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uvo.getUserNum());

			ResultSet crs = pstmt.executeQuery();

			if(crs.next()) {
				cnt=crs.getInt(1);
			}
			crs.close();

			datas.setRlist(rlist);
			datas.setReplyCnt(cnt);

			System.out.println("datas : "+datas);
		} catch (SQLException e) {
			System.out.println("replyDAO myReply���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}	
		return datas;
	}
	//========================================================================================
	@SuppressWarnings("resource")
	public ReplySet getDBData(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		ReplySet data = new ReplySet();
		ReplyVO rvo = null;
		try {
			// "SELECT * FROM BOARD_REPLY WHERE R_ID=?";
			pstmt = conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setInt(1, vo.getrId());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				rvo = new ReplyVO();

				rvo.setrId(rs.getInt("R_ID"));				// ��� PK
				rvo.setbId(rs.getInt("B_ID"));				// �Խñ� ��ȣ
				rvo.setUserNum(rs.getInt("USER_NUM"));		// ���� ��ȣ
				rvo.setrContent(rs.getString("R_CONTENT"));	// ��� ����
				rvo.setrDate(rs.getDate("R_DATE"));			// �ۼ���
				rvo.setDeleteAt(rs.getString("DELETE_AT"));	// ��������
				rvo.setrWriter(rs.getString("R_WRITER"));	// �ۼ���
				rvo.setParentId(rs.getInt("PARENT_ID"));	// ��� ���� ����

				rs.close();

				ArrayList<ReplyVO> rrlist = new ArrayList<ReplyVO>();

				if(rvo.getParentId() > 0) {
					String sql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rvo.getrId()); // ������ parent�� ����� ID
					ResultSet rrs = pstmt.executeQuery();
					// ����

					while (rrs.next()) {
						ReplyVO rrvo = new ReplyVO();

						rrvo.setrId(rrs.getInt("R_ID"));				// ��� PK
						rrvo.setbId(rrs.getInt("B_ID"));				// �Խñ� ��ȣ
						rrvo.setUserNum(rrs.getInt("USER_NUM"));		// ���� ��ȣ
						rrvo.setrContent(rrs.getString("R_CONTENT"));	// ��� ����
						rrvo.setrDate(rrs.getDate("R_DATE"));			// �ۼ���
						rrvo.setDeleteAt(rrs.getString("DELETE_AT"));	// ��������
						rrvo.setrWriter(rrs.getString("R_WRITER"));		// �ۼ���
						rrvo.setParentId(rrs.getInt("PARENT_ID"));		// ��� ���� ����	
						rrlist.add(rrvo);
					}
					rrs.close();
				}
				data.setRvo(rvo);
				data.setRrlist(rrlist);
			}
		} catch (SQLException e) {
			System.out.println("ReplySet getDBDate���� ���");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	//========================================================================================	
	@SuppressWarnings("resource")
	public boolean insert(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		// Ʈ������ Ȯ��
		boolean check = false;
		try {
			// (SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),?,?,?,?,?)
			// ? �Է°� : (B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID)
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getbId());			// ���� ID
			pstmt.setInt(2, vo.getUserNum());		// �����ѹ�
			pstmt.setString(3, vo.getrContent());	// ��۳���
			pstmt.setString(4, vo.getrWriter());	// �ۼ���
			pstmt.setInt(5, vo.getParentId());		// ��� ���� ���� �θ� ��
			pstmt.executeUpdate();

			String sql = "UPDATE BOARD SET RE_CNT = RE_CNT+1 WHERE B_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbId());
			pstmt.executeUpdate();
			check = true;

			if (check) {
				conn.commit();
				res= true;
			}
			else {
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println("replyDAO insert���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//========================================================================================
	@SuppressWarnings("resource")
	public boolean delete(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		String sql;
		int cnt=0; // ���� ���� Ȯ��

		// Ŀ��Ȯ�ο�
		boolean check = false;

		try {
			// ���� Ŀ�� ����
			conn.setAutoCommit(false);
			// ����̶��
			if (vo.getParentId() == 0) {

				sql ="SELECT COUNT(*) FROM BOARD_REPLY WHERE PARENT_ID=?";
				// sql = "SELECT * FROM BOARD_REPLY WHERE PARENT_ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getrId());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					cnt=rs.getInt(1);
				}
				System.out.println("����϶� �� �� ���� ���� : "+cnt);

				// ���࿡ ��ۿ� ������ ������ �ش� ��� ����
				if (cnt == 0) {
					sql = "DELETE FROM BOARD_REPLY WHERE R_ID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, vo.getrId());
					pstmt.executeUpdate();
					System.out.println("���� ������ ��ۻ��� ���");
				}
				// ������ ������ DELETE_AT Y�� ����
				else {
					pstmt = conn.prepareStatement(sql_DELETE);
					pstmt.setInt(1, vo.getrId());
					pstmt.executeUpdate();
					System.out.println("���� ������ ������Ʈ ���");
				}
			}
			// ���� �̶��
			else {
				sql = "DELETE FROM BOARD_REPLY WHERE R_ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getrId());
				pstmt.executeUpdate();
				System.out.println("���� �϶� ���� ���");
			}

			// ��� �� 1 ����
			sql = "UPDATE BOARD SET RE_CNT = RE_CNT-1 WHERE B_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbId());
			pstmt.executeUpdate();
			System.out.println("��ۼ� �����Ϸ�");

			check = true;

			if (check) {
				conn.commit();
				res= true;
				System.out.println("Ŀ�ԿϷ�");
			}
			else {
				conn.rollback();
				System.out.println("�ѹ�");
			}

		} catch (SQLException e) {
			System.out.println("ReplyDAO delete���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//========================================================================================	
	public boolean update(ReplyVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getrContent());
			pstmt.setInt(2, vo.getrId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("ReplyDAO update���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}


}

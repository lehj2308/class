package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

public class BoardDAO {

	// �Խñ� ���� �ۼ� 
	// �Է� �� : (B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG)
	// DEFAULT �ڵ� �Է°�(B_DATE, B_HIT, RE_CNT)
	static String sql_INSERT = "INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) "
			+ "VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)";
	// �Խñ� ����
	static String sql_DELETE = "DELETE FROM BOARD WHERE B_ID=?";
	// �Խñ� ������Ʈ 
	// ���氡�� ���� : (ī�װ�, Ÿ��Ʋ, ������(�Խñ� ����), ���α׷��� ���) 
	// ����Ұ� : (B_ID, USER_NUM, �ۼ���,�ۼ���, ��ۼ�, ��ȸ��)
	static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_ID=?";
	// ���� ID �� �Խñ� ��ü���
	//static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //������� ����¡

	// ���ڽ� ����¡
	/*static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
			+ "BOARD.* FROM BOARD WHERE USER_NUM=? AND ROWNUM<=? ORDER BY ? DESC, B_ID DESC) WHERE ?<RNUM AND B_TITLE LIKE '%'||?||'%'";*/
	static String sql_SELECT_ALL_USER;

	// ���� �ֽ� selectAll_USER ******
	/*static String sql_SELECT_ALL_USER = "SELECT * FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
			+ "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
			+ ") BOARD WHERE ROWNUM <= ?" //����
			+ ") WHERE RNUM > ? ORDER BY ? DESC, B_DATE DESC";*/

	static String sql_SELECT_ALL;

	// ���� �ֽ� selectAll ******
	/*static String sql_SELECT_ALL = "SELECT * FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
			+ "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
			+ ") board WHERE ROWNUM <= ?" //����
			+ ") WHERE RNUM > ? ORDER BY RE_CNT DESC, B_DATE DESC";*/

	// �Խñ� ��ü���
	/*static String sql_SELECT_ALL = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
			+ "BOARD.* FROM BOARD WHERE B_CTGR=? AND ROWNUM<=? ORDER BY ? DESC, B_ID DESC) WHERE ?<RNUM AND B_TITLE LIKE '%'||?||'%'";*/

	// �Խñ� �ϳ� ���
	static String sql_SELECT_ONE = "SELECT * FROM BOARD WHERE B_ID=?";
	//=====================================================================================
	// selectAll BoardVO�� ����ִ� datas ��ȯ

	static int pageSize = 3; // ������ 10���� ��� ��°��� �ٲٽǶ� ���� �ٲٽø� �˴ϴ�.

	// userNum �� ���� �� 0�־��ּ���
	
	 @SuppressWarnings("resource")
	   public BoardSet getDBList(UsersVO uvo, BoardVO vo, String pageOrder, int pageNum) {
	      Connection conn = JNDI.getConnection();
	      PreparedStatement pstmt = null;
	      BoardSet datas = new BoardSet();
	      // �Խñ��� ���� ����Ʈ + ��ü �Խù� cnt;
	      String sql;
	      int cnt=0;

	      int startNum = pageNum*pageSize;
	      int lastNum = (pageNum*pageSize)+pageSize;

	      // System.out.println(uvo);
	      System.out.println(vo);

	      try {
	         // �α��� ���̵� �ִٸ� (�� �ۺ���)
	         if (uvo.getUserNum() > 0) {
	            if(pageOrder.equals("reply")) {
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR =? ORDER BY RE_CNT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY RE_CNT DESC";
	               System.out.println("��ۼ� ���");
	            }
	            else if(pageOrder.equals("hit")){
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR = ? ORDER BY B_HIT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY B_HIT DESC";
	               System.out.println("��ȸ�� ���");
	            }
	            else {
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR = ?ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY '%' DESC";
	               System.out.println("��ü ���");
	            }


	            pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
	            pstmt.setInt(1, uvo.getUserNum());
	            pstmt.setString(2, vo.getbTitle());
	            pstmt.setString(3, vo.getbCtgr());
	            pstmt.setInt(4, lastNum);    // max ��ȣ
	            pstmt.setInt(5, startNum);       // start ��ȣ

	         }
	         // ��ü ���
	         else {

	            if(pageOrder.equals("reply")) {
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY RE_CNT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY RE_CNT DESC";
	               System.out.println("��ۼ� ���");
	            }
	            else if(pageOrder.equals("hit")){
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY B_HIT DESC";
	               System.out.println("��ȸ�� ���");
	            }
	            else {
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //����
	                     + ") WHERE RNUM > ? ORDER BY B_DATE DESC";
	               System.out.println("��ü ���");
	            }

	            pstmt = conn.prepareStatement(sql_SELECT_ALL);
	            pstmt.setString(1, vo.getbCtgr());
	            pstmt.setString(2, vo.getbTitle());
	            pstmt.setInt(3, lastNum);    // max ��ȣ
	            /*if(pageOrder.equals("��ۼ�")) {
	               pstmt.setString(3, "RE_CNT");
	               System.out.println("��ۼ� ���");
	            }
	            else if(pageOrder.equals("��ȸ��")){
	               pstmt.setString(3, "B_HIT");
	               System.out.println("��ȸ�� ���");
	            }
	            else {
	               pstmt.setString(3, "'%'");
	               System.out.println("��ü ���");
	            }*/
	            pstmt.setInt(4, startNum);       // start ��ȣ
	         }

	         ResultSet rs = pstmt.executeQuery();
	         BoardVO bvo = null;
	         ArrayList<BoardVO>blist = new ArrayList<BoardVO>();

	         while(rs.next()) {
	            bvo = new BoardVO();

	            bvo.setbId(rs.getInt("B_ID"));            // �Խñ� ��ȣ
	            bvo.setUserNum(rs.getInt("USER_NUM"));       // �ۼ� ���� ��ȣ
	            bvo.setbCtgr(rs.getString("B_CTGR"));       // �Խñ� ī�װ�
	            bvo.setbTitle(rs.getString("B_TITLE"));      // �Խñ� ����
	            bvo.setbContent(rs.getString("B_CONTENT")); // �Խñ� ����
	            bvo.setbWriter(rs.getString("B_WRITER"));    // �۾���
	            bvo.setbDate(rs.getDate("B_DATE"));       // �ۼ���
	            bvo.setbHit(rs.getInt("B_HIT"));          // ��ȸ��
	            bvo.setbLang(rs.getString("B_LANG"));       // ���α׷��� ���
	            bvo.setReCnt(rs.getInt("RE_CNT"));          // ��� �� 

	            blist.add(bvo);
	            System.out.println("bvo Ȯ�� : "+bvo);
	            
	         }
	         rs.close();

	         // System.out.println("blist Ȯ�� : "+blist);

	         if(uvo.getUserNum() > 0) {
	            // ���� �� �� ��ü ���� ���
	            sql = "SELECT COUNT(*) FROM BOARD WHERE B_CTGR=? AND USER_NUM=? AND B_TITLE LIKE '%'||?||'%'";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, vo.getbCtgr());
	            pstmt.setInt(2, uvo.getUserNum());
	            pstmt.setString(3, vo.getbTitle());
	         }
	         else {
	            // ī�װ��� ��ü ���� ���
	            sql = "SELECT COUNT(*) FROM BOARD WHERE B_CTGR=? AND B_TITLE LIKE '%'||?||'%'";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, vo.getbCtgr());
	            pstmt.setString(2, vo.getbTitle());
	         }
	         
	         ResultSet crs = pstmt.executeQuery();
	         if(crs.next()) {
	            cnt=crs.getInt(1);
	         }
	         crs.close();   
	         
	         datas.setBlist(blist);
	         datas.setBoardCnt(cnt);

	      } catch(SQLException e) {
	         System.out.println("boardDAO getDBList���� �߻�");
	         e.printStackTrace();
	      }
	      // System.out.println(datas);
	      return datas;
	   }

	//=====================================================================================
	public BoardVO getDBData(UsersVO uvo, BoardVO bvo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		BoardVO data = new BoardVO();
		boolean check = false;
		try {

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setInt(1, bvo.getbId());

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {

				data.setbId(rs.getInt("B_ID"));				// �Խñ� ��ȣ
				data.setUserNum(rs.getInt("USER_NUM")); 		// �ۼ� ���� ��ȣ
				data.setbCtgr(rs.getString("B_CTGR")); 		// �Խñ� ī�װ�
				data.setbTitle(rs.getString("B_TITLE"));		// �Խñ� ����
				data.setbContent(rs.getString("B_CONTENT")); // �Խñ� ����
				data.setbWriter(rs.getString("B_WRITER")); 	// �۾���
				data.setbDate(rs.getDate("B_DATE")); 		// �ۼ���
				data.setbHit(rs.getInt("B_HIT")); 			// ��ȸ��
				data.setbLang(rs.getString("B_LANG")); 		// ���α׷��� ���
				data.setReCnt(rs.getInt("RE_CNT")); 			// ��� �� 
				
				// System.out.println(data);
			}
			rs.close();

			// ���� �� ���̸� ��ȸ�� ���� ����
			if(uvo.getUserNum() == data.getUserNum()) {
				System.out.println("uvo.getUserNum() : "+data.getUserNum() );
				System.out.println("bvo.getUserNum() : "+data.getUserNum() );
				System.out.println("BoardDAO.getdbDATe ��ȸ�� ���� x");
				check = true;
			}
			else {
				System.out.println("BoardDAO.getdbDATe ��ȸ�� ���� o");
				String sql= "UPDATE BOARD SET B_HIT=B_HIT+1 WHERE B_ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bvo.getbId());
				pstmt.executeUpdate();
				check = true;
			}
			
			if (check) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO getDBData���� �߻�");
			e.printStackTrace();
		}
		System.out.println("BoardDAO selectOne : "+data);
		return data;
	}
	//=====================================================================================
	public boolean insert(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			// (SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)
			// ? �Է� �� : (USER_NUM, B_CTGR, B-TITLE, B_CONTENT, B_WRITER, B_LANG) 
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getUserNum()); 		// �����ѹ�
			pstmt.setString(2, vo.getbCtgr());		// ī�װ�
			pstmt.setString(3, vo.getbTitle());		// Ÿ��Ʋ
			pstmt.setString(4, vo.getbContent());	// ����
			pstmt.setString(5, vo.getbWriter());	// �۾���
			pstmt.setString(6, vo.getbLang());		// ���α׷��� ���
			pstmt.executeUpdate();
			res=true;
			
		} catch(SQLException e) {
			System.out.println("BoardDAO insert���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//=====================================================================================
	public boolean update(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt=null;
		boolean res = false;
		try {
			// ���氡�� ���� : (ī�װ�, Ÿ��Ʋ, ������(�Խñ� ����), ���α׷��� ���) 
			// ����Ұ� : (B_ID, USER_NUM, �ۼ���,�ۼ���, ��ۼ�, ��ȸ��)
			// B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_NUM=?
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getbCtgr());
			pstmt.setString(2, vo.getbTitle());
			pstmt.setString(3, vo.getbContent());
			pstmt.setString(4, vo.getbLang());
			pstmt.setInt(5, vo.getbId());
			pstmt.executeUpdate();
			res=true;
		} catch (SQLException e) {
			System.out.println("BoardDAO update���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//=====================================================================================
	public boolean delete(BoardVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		boolean res = false;
		try {
			pstmt = conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getbId());	// ���̵� ������ ����
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("BoardDAO delete���� �߻�");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;
	}
	//=====================================================================================
	// ī�װ��� �Խñ� ����
	/* public int boardCnt(BoardVO bvo, UsersVO uvo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM BOARD WHERE B_CTGR=? AND USER_NUM LIKE '%'||?||'%'";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getbCtgr());
			pstmt.setInt(2, uvo.getUserNum());

			ResultSet rs = pstmt.executeQuery();
			cnt=rs.getInt(1);

			rs.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO boardCnt���� �߻�");
			e.printStackTrace();
		}
		return cnt;
	}*/
}


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
   static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_ID=?"; // B_NUM ��Ÿ�־���!
   // ���� ID �� �Խñ� ��ü���
   //static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //������� ����¡
   // ���ڽ� ����¡
   static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
         + "BOARD.* FROM BOARD WHERE USER_NUM=? AND ROWNUM<=? ORDER BY B_DATE DESC) WHERE ?<=RNUM AND B_TITLE LIKE '%'||?||'%'";

   // �Խñ� ��ü���
   static String sql_SELECT_ALL = "select * from (select a.*, rownum as rnum from(select * from board where b_ctgr = ? and B_TITLE LIKE '%' || ? || '%' order by b_date desc) a) where (rnum <=? and rnum >=?) ";
 // ��Ÿ�־���! BDATE
   // �Խñ� �ϳ� ���
   static String sql_SELECT_ONE = "SELECT * FROM BOARD WHERE B_ID=?";  // ��Ÿ�־���
   //=====================================================================================
   // selectAll BoardVO�� ����ִ� datas ��ȯ

   static int pageSize = 2; // ������ 10���� ��� ��°��� �ٲٽǶ� ���� �ٲٽø� �˴ϴ�.

   // userNum �� ���� �� 0�־��ּ���
   public ArrayList<BoardVO> getDBList(UsersVO uvo, BoardVO vo, int pageNum) {
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
      System.out.println("boardDAO getDBList���� ����");
      try {
         // �α��� ���̵� �ִٸ� (�� �ۺ���)
         if (uvo.getUserNum() > 0) {
            pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
            pstmt.setInt(1, uvo.getUserNum());
            pstmt.setInt(3, (pageNum*pageSize)+pageSize);    // max ��ȣ
            pstmt.setInt(4, pageNum*pageSize);       // start ��ȣ
            pstmt.setString(4, vo.getbContent());
       
         }
         // ��ü ���
         else {
        	 System.out.println("ī�װ� : "+ vo.getbCtgr());
        	
            pstmt = conn.prepareStatement(sql_SELECT_ALL);
            pstmt.setString(1, vo.getbCtgr());
            pstmt.setInt(3, (pageNum*pageSize)+pageSize);    // max ��ȣ
            pstmt.setInt(4, pageNum*pageSize+1);       // start ��ȣ
            pstmt.setString(2,vo.getbTitle());
         }

         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
        	// System.out.println("Ȯ��");
            BoardVO bvo = new BoardVO();

            bvo.setbId(rs.getInt("B_ID"));            // �Խñ� ��ȣ
            bvo.setUserNum(rs.getInt("USER_NUM"));       // �ۼ� ���� ��ȣ
            bvo.setbCtgr(rs.getString("B_CTGR"));       // �Խñ� ī�װ�
            bvo.setbTitle(rs.getString("B_TITLE"));      // �Խñ� ����
            bvo.setbContent(rs.getString("B_CONTENT")); // �Խñ� ����
            bvo.setbWriter(rs.getString("B_WRITER"));    // �۾���
            bvo.setbDate(rs.getDate("B_DATE"));       // �ۼ���
            bvo.setbHit(rs.getInt("B_HIT"));          // ��ȸ��
            bvo.setbLang(rs.getString("B_LANG"));       // ���α׷��� ���
            bvo.setReCnt(rs.getInt("B_COUNT"));          // ��� �� 

            datas.add(bvo);
         }
         rs.close();
      } catch(SQLException e) {
         System.out.println("boardDAO getDBList���� �߻�");
         e.printStackTrace();
      }
      return datas;
   }
   //=====================================================================================
   public BoardVO getDBData(BoardVO vo) {
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      BoardVO bvo = null;  // data ���� �ʿ� ��� �����߽��ϴ�!.
      try {
         pstmt = conn.prepareStatement(sql_SELECT_ONE);
         pstmt.setInt(1, vo.getbId());

         ResultSet rs = pstmt.executeQuery();

         if(rs.next()) {
        	System.out.println("Ȯ��");
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
            bvo.setReCnt(rs.getInt("B_COUNT"));          // ��� ��  // ��Ÿ�־���

         }
         rs.close();
      } catch (SQLException e) {
         System.out.println("BoardDAO getDBData���� �߻�");
         e.printStackTrace();
      }
      return bvo; // �����߽��ϴ�!
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
         pstmt.setInt(1, vo.getUserNum());       // �����ѹ�
         pstmt.setString(2, vo.getbCtgr());      // ī�װ�
         pstmt.setString(3, vo.getbTitle());      // Ÿ��Ʋ
         pstmt.setString(4, vo.getbContent());   // ����
         pstmt.setString(5, vo.getbWriter());   // �۾���
         pstmt.setString(6, vo.getbLang());      // ���α׷��� ���
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
      System.out.println("BoardDAO update()");
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
         System.out.println("BID :"+ vo.getbId());
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
         pstmt.setInt(1, vo.getbId());   // ���̵� ������ ����
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


}
package model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

/* REPLY ���̺� �÷����Դϴ�.
 *    R_ID INT PRIMARY KEY,
   T_ID INT NOT NULL,
   USER_NUM INT NOT NULL,
   R_CONTENT VARCHAR(225) NOT NULL,
   R_DATE DATE DEFAULT SYSDATE,
   DELETE_AT VARCHAR(1) DEFAULT 'N',
   R_WRITER VARCHAR(20) NOT NULL,
   PARENT_ID INT NOT NULL,
   constraint t_id_cons foreign key (t_id) references test(t_id) on delete cascade,
   CONSTRAINT user_num_cons3 FOREIGN KEY (USER_NUM) REFERENCES users(user_num) on delete cascade
 * */

public class TestReplyDAO {

   /*
    * �� �κ��� �����ϼ���! (SQL�� ������� ���� �����) static String sql_SELECT_ALL_TEST =
    * "SELECT * FROM (SELECT * FROM TEST ORDER BY T_DATE) WHERE ROWNUM <= ?";
    * static String sql_SELECT_ALL_MYTEST =
    * "SELECT * FROM (SELECT * FROM TEST ORDER BY T_DATE) WHERE USER_NUM=? AND ROWNUM <= ?"
    * ; static String sql_SELECT_ALL_REPLY =
    * "SELECT * FROM (SELECT * FROM TEST ORDER BY T_DATE) WHERE ROWNUM <= ?";
    * static String sql_SELECT_ALL_RREPLY =
    * "SELECT * FROM (SELECT * FROM TEST ORDER BY T_DATE) WHERE ROWNUM <= ?";
    */

   // INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER, T_WRITER,
   // T_LANG)
   // VALUES ((select NVL(MAX(T_ID),0)+1 from
   // TEST),1,'�ڵ�����1','��������1','��1','je','JAVA');
   /*
    * static String sql_INSERT =
    * "INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER, T_EX, T_WRITER, T_LANG) "
    * + "VALUES ((SELECT NVL(MAX(T_ID),0)+1 FROM TEST),?,?,?,?,?,?,?)"; //update
    * message set favcount= favcount+1 where mid=? static String sql_UPDATE =
    * "UPDATE TEST SET T_TITLE=?, T_CONTENT=?, T_ANSWER=?, T_EX=?, T_HIT=?, T_LANG=?, RE_CNT=? WHERE T_ID=?"
    * ; static String sql_DELETE = "DELETE FROM TEST WHERE T_ID =?";
    */

   // getDBList ��+��� = 1:N
   public ArrayList<TestSet> selectAll(UsersVO uvo, int pageCnt, TestReplyVO vo) { // pageCnt : ����¡���� ����
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      String sql;

      ArrayList<TestSet> datas = new ArrayList<TestSet>();
//      TestSet ts = new TestSet();
      ArrayList<TestReplyVO> rrlist = null;
      TestReplyVO rvo = null;

      try { // ��ü ��
         if (uvo.getUserNum() > 0) { // �α����� �޴ٸ�
            // *���� �� -> sql = "SELECT * FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=? AND
            // PARENT_ID=0 ORDER BY R_DATE DESC) WHERE ROWNUM>? AND ROWNUM <= ?";
            /*
             * 1�� ���� (ù��° ROWNUM���� 0,1 �ƴϸ� ������ �ȸ���) -> sql =
             * "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END, "
             * +
             * "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
             * +
             * "PARENT_ID FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=0 ORDER BY R_DATE DESC) "
             * + "WHERE ROWNUM>? AND ROWNUM <= ?";
             */

            // SELECT * FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE
            // USER_NUM=1 AND PARENT_ID=0 AND ROWNUM <= 5 ORDER BY R_DATE DESC) WHERE
            // 3<=RNUM;
            sql = "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, "
                  + "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
                  + "PARENT_ID FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE USER_NUM=? "
                  + "AND T_ID=? AND PARENT_ID=0 AND ROWNUM<=? ORDER BY R_DATE DESC) WHERE ?<=RNUM";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uvo.getUserNum());
            pstmt.setInt(2,vo.gettId());
            pstmt.setInt(3, (pageCnt * 10) + 10);
            pstmt.setInt(4, pageCnt * 10);
         }

         else { // ��ü (�α��� ���� ����)
            sql = "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, "
                  + " R_CONTENT , R_DATE, DELETE_AT, "
                  + "PARENT_ID FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE T_ID=? AND PARENT_ID=0 AND ROWNUM<=? "
                  + "ORDER BY R_DATE DESC) WHERE ?<=RNUM";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.gettId());
            pstmt.setInt(2, (pageCnt * 10) + 10);
            pstmt.setInt(3, pageCnt * 10);
         }

         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {
        	 TestSet ts = new TestSet();//��ġ����!
            rvo = new TestReplyVO(); // ���

            // ��� �� �־���
            rvo.setrId(rs.getInt("R_ID"));
            rvo.settId(rs.getInt("T_ID"));
            rvo.setUserNum(rs.getInt("USER_NUM"));
            rvo.setrContent(rs.getString("R_CONTENT"));
            rvo.setrDate(rs.getDate("R_DATE"));
            rvo.setDeleteAt(rs.getString("DELETE_AT"));
            rvo.setrWriter(rs.getString("CASEWHENDELETE_AT='Y'THEN'UNKNOWN'ELSER_WRITEREND"));
            rvo.setParentId(rs.getInt("PARENT_ID"));

            //rs.close();

          //  System.out.println("reply Ȯ��: " + rvo); // �α�

            // ���� ������ ����
         //   if (rvo.getParentId() > 0) { // ���� �����Ѵٸ� // �� �κ� ���־��մϴ�!
               sql = "SELECT * FROM TEST_REPLY WHERE PARENT_ID=? ORDER BY R_DATE DESC";

               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, rvo.getrId());

               ResultSet rrs = pstmt.executeQuery();
               rrlist = new ArrayList<TestReplyVO>();

               while (rrs.next()) {
                  TestReplyVO rrvo = new TestReplyVO();
                  //rrlist = new ArrayList<ReplyVO>();

                  rrvo.setrId(rrs.getInt("R_ID"));
                  rrvo.settId(rrs.getInt("T_ID"));
                  rrvo.setUserNum(rrs.getInt("USER_NUM"));
                  rrvo.setrContent(rrs.getString("R_CONTENT"));
                  rrvo.setrDate(rrs.getDate("R_DATE"));
                  rrvo.setDeleteAt(rrs.getString("DELETE_AT"));
                  rrvo.setrWriter(rrs.getString("R_WRITER"));
                  rrvo.setParentId(rrs.getInt("PARENT_ID"));
                  rrlist.add(rrvo); // ��� ����Ʈ
                  System.out.println("rrlist Ȯ��: " + rrlist);
               }

               rrs.close();

            
            ts.setReply(rvo);
            ts.setRrlist(rrlist);
            datas.add(ts);

            System.out.println("datas Ȯ��: " + datas);

         }
         rs.close();

      } catch (Exception e) {
         System.out.println("TestDAO-selectDBList ����");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return datas;

   }

/////////////////////////////////////////////////////////////////////////         

   // getDBData
   public TestSet getDBData(TestReplyVO vo) { // testSet���� ����
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      TestSet data = null;
      ArrayList<TestReplyVO> rrlist = null;
      TestReplyVO rvo = null;

      String sql;

      try {
         // *���� �Ϲ� -> sql = "SELECT * FROM TEST_REPLY WHERE R_ID=?";

         /*
          * SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE
          * R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY WHERE
          * R_ID=2; ==> SQL���� : CASE WHEN X=Y THEN A ELSE B END : X=Y�� TRUE�� A�̰� �׷��� ������
          * B��.
          */

         sql = "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, "
               + "CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
               + "PARENT_ID FROM TEST_REPLY WHERE R_ID=?";

         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, vo.getrId());
         ResultSet rs = pstmt.executeQuery();

         if (rs.next()) {
            rvo = new TestReplyVO(); // ���

            rvo.setrId(rs.getInt("R_ID"));
            rvo.settId(rs.getInt("T_ID"));
            rvo.setUserNum(rs.getInt("USER_NUM"));
            rvo.setrContent(rs.getString("R_CONTENT"));
            rvo.setrDate(rs.getDate("R_DATE"));
            rvo.setDeleteAt(rs.getString("DELETE_AT"));
            rvo.setrWriter(rs.getString("R_WRITER"));
            rvo.setParentId(rs.getInt("PARENT_ID"));

            rs.close();

            System.out.println("reply Ȯ��: " + rvo); // �α�

            // ���� ������ ����
            if (rvo.getParentId() > 0) { // ���� �����Ѵٸ�
               sql = "SELECT * FROM TEST_REPLY WHERE PARENT_ID=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, rvo.getrId()); // praent_id == ����� rid

               ResultSet rrs = pstmt.executeQuery();

               while (rrs.next()) {
                  TestReplyVO rrvo = new TestReplyVO();
                  rrlist = new ArrayList<TestReplyVO>();

                  rrvo.setrId(rrs.getInt("R_ID"));
                  rrvo.settId(rrs.getInt("T_ID"));
                  rrvo.setUserNum(rrs.getInt("USER_NUM"));
                  rrvo.setrContent(rrs.getString("R_CONTENT"));
                  rrvo.setrDate(rrs.getDate("R_DATE"));
                  rrvo.setDeleteAt(rrs.getString("DELETE_AT"));
                  rrvo.setrWriter(rrs.getString("R_WRITER"));
                  rrvo.setParentId(rrs.getInt("PARENT_ID"));
                  rrlist.add(rrvo); // ���� ����Ʈ

                  System.out.println("rrlist Ȯ��: " + rrlist);
               }
               rrs.close();
            }

            data.setReply(rvo);
            data.setRrlist(rrlist);
         }

      } catch (SQLException e) {
         System.out.println("ReplyDAO-getDBData �����α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return data;

   }

/////////////////////////////////////////////////////////////////////////         

   // insert --> ��� ��(RE_CNT) update Ʈ�����!!!!!!��
   public boolean insert(TestReplyVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      boolean check = false; // Ʈ����� Ŀ��, �ѹ� ���� �Ǵ� ����

      String sql_INSERT = "INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) "
            + "VALUES ((SELECT NVL(MAX(R_ID),0)+1 FROM TEST_REPLY),?,?,?,?,?)";

      String sql_RECNT_UP = "UPDATE TEST SET RE_CNT= RE_CNT+1 WHERE T_ID=?";
      // ���/������ �޸��� Ư�� TEST�Խù��� ��� �� (RE_CNT) ++

      try {
         conn.setAutoCommit(false);

         pstmt = conn.prepareStatement(sql_INSERT);

         pstmt.setInt(1, vo.gettId());
         pstmt.setInt(2, vo.getUserNum());
         pstmt.setString(3, vo.getrContent());
         pstmt.setString(4, vo.getrWriter());
         pstmt.setInt(5, vo.getParentId());
         pstmt.executeUpdate();

         pstmt = conn.prepareStatement(sql_RECNT_UP);
         pstmt.setInt(1, vo.gettId());
         pstmt.executeUpdate();
         check = true;

         if (check) {
            conn.commit();
            res = true;
         } else {
            conn.rollback();
         }

      } catch (SQLException e) {
         System.out.println("ReplyDAO-insert ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }

/////////////////////////////////////////////////////////////////////////

// delete 
   public boolean delete(TestReplyVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      boolean check = false; // Ʈ����� Ŀ��, �ѹ� ���� �Ǵ� ����

      // ** ��� ������ ��� --> ��¥ ������ �ƴ� ,,, UPDATE ���� (DELETE_AT = 'N' --> 'Y')
      String sql_DELETE_R1 = "DELETE FROM TEST_REPLY R_ID=?";
      String sql_DELETE_R2 = "UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =? AND PARENT_ID=0";

      // ** ���� ������ ��� --> ��¥ ����
      String sql_DELETE_RR = "DELETE FROM TEST_REPLY WHERE R_ID =? AND PARENT_ID=?";

      // ��۴޸� Ư�� TEST�Խù��� ��� �� (RE_CNT) --
      String sql_RECNT_DN = "UPDATE TEST SET RE_CNT= RE_CNT-1 WHERE T_ID=?";

      String sql_COUNT = "SELECT COUNT(*) FROM TEST_REPLY WHERE PARENT_ID=?";

      int cnt = 0;

      try {
         if (vo.getParentId() == 0) {

            // ������ �޸� ������, ���� ���� ������� Ȯ��

            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql_COUNT);
            pstmt.setInt(1, vo.getrId());
            ResultSet rs = pstmt.executeQuery();

            cnt = rs.getInt(1);

            if (cnt == 0) { // ���� ���� ��� ���� ��, -> ���� ����
               pstmt = conn.prepareStatement(sql_DELETE_R1); // ���� DB���� ����
               pstmt.setInt(1, vo.getrId());
               pstmt.executeUpdate();
               System.out.println("���� ���� ��� ���� �Ϸ� �α�");

            } else { // ������ �ִ� ��� ���� -> DELETE_AT�� "N -> Y"�� ����
               pstmt = conn.prepareStatement(sql_DELETE_R2);
               pstmt.setInt(1, vo.getrId());
               pstmt.executeUpdate();
               System.out.println("���� �ִ� ��� ���� �Ϸ� �α�");

            }

         } else { // ���� ����� "����"�̶��,
            pstmt = conn.prepareStatement(sql_DELETE_RR);
            pstmt.setInt(1, vo.getrId());
            pstmt.executeUpdate();

         }

         pstmt = conn.prepareStatement(sql_RECNT_DN); // �ش� �Խù� ��ۼ� -1�ϱ� => ���, ��� ���� �� �Խñ� ��� �� --
         pstmt.setInt(1, vo.gettId());
         pstmt.executeUpdate();

         check = true; // Ŀ�� �ϱ�����

         if (check) {
            conn.commit();
            res = true;
         } else {
            conn.rollback();
         }

      } catch (SQLException e) {
         System.out.println("ReplyDAO-delete ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;

   }

/////////////////////////////////////////////////////////////////////////   

   // update
   public boolean update(TestReplyVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      // ��� ����
      String sql_UPDATE_R = "UPDATE TEST_REPLY SET T_ID=?, USER_NUM=?, R_CONTENT=?, R_WRITER=?, PARENT_ID=0 WHERE R_ID =?";

      // ���� ����
      String sql_UPDATE_RR = "UPDATE TEST_REPLY SET T_ID=?, USER_NUM=?, R_CONTENT=?,  R_WRITER=?, PARENT_ID=? WHERE R_ID =?";

      try {

         if (vo.getParentId() == 0) { // ���� ����� "���"�̶��,

            pstmt = conn.prepareStatement(sql_UPDATE_R);
            pstmt.setInt(1, vo.gettId());
            pstmt.setInt(2, vo.getUserNum());
            pstmt.setString(3, vo.getrContent());
            pstmt.setString(4, vo.getrWriter());
            pstmt.setInt(5, vo.getrId());
            pstmt.executeUpdate();

            System.out.println("��� ���� Ȯ��"); // �α�

         } else { // ���� ����� "����"�̶��,

            pstmt = conn.prepareStatement(sql_UPDATE_RR);
            pstmt.setInt(1, vo.gettId());
            pstmt.setInt(2, vo.getUserNum());
            pstmt.setString(3, vo.getrContent());
            pstmt.setString(4, vo.getrWriter());
            pstmt.setInt(5, vo.getParentId());
            pstmt.setInt(6, vo.getrId());
            pstmt.executeUpdate();

            System.out.println("���� ���� Ȯ��"); // �α�
         }

         res = true;

      } catch (SQLException e) {
         System.out.println("ReplyDAO-update ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }

}
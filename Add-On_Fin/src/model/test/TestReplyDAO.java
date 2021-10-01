package model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

/* REPLY 테이블 컬럼명입니다.
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
    * 이 부분은 무시하세요! (SQL문 상단으로 뺄지 고민중) static String sql_SELECT_ALL_TEST =
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
   // TEST),1,'코딩문제1','문제내용1','답1','je','JAVA');
   /*
    * static String sql_INSERT =
    * "INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER, T_EX, T_WRITER, T_LANG) "
    * + "VALUES ((SELECT NVL(MAX(T_ID),0)+1 FROM TEST),?,?,?,?,?,?,?)"; //update
    * message set favcount= favcount+1 where mid=? static String sql_UPDATE =
    * "UPDATE TEST SET T_TITLE=?, T_CONTENT=?, T_ANSWER=?, T_EX=?, T_HIT=?, T_LANG=?, RE_CNT=? WHERE T_ID=?"
    * ; static String sql_DELETE = "DELETE FROM TEST WHERE T_ID =?";
    */

   // getDBList 댓+대댓 = 1:N
   public ArrayList<TestSet> selectAll(UsersVO uvo, int pageCnt, TestReplyVO vo) { // pageCnt : 페이징관련 변수
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      String sql;

      ArrayList<TestSet> datas = new ArrayList<TestSet>();
//      TestSet ts = new TestSet();
      ArrayList<TestReplyVO> rrlist = null;
      TestReplyVO rvo = null;

      try { // 전체 글
         if (uvo.getUserNum() > 0) { // 로그인을 햇다면
            // *수정 전 -> sql = "SELECT * FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=? AND
            // PARENT_ID=0 ORDER BY R_DATE DESC) WHERE ROWNUM>? AND ROWNUM <= ?";
            /*
             * 1차 수정 (첫번째 ROWNUM값이 0,1 아니면 데이터 안먹힘) -> sql =
             * "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END, "
             * +
             * "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
             * +
             * "PARENT_ID FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=0 ORDER BY R_DATE DESC) "
             * + "WHERE ROWNUM>? AND ROWNUM <= ?";
             */

            // SELECT * FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE
            // USER_NUM=1 AND PARENT_ID=0 AND ROWNUM <= 5 ORDER BY R_DATE DESC) WHERE
            // 3<=RNUM;
            sql = "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, "
                  + "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
                  + "PARENT_ID FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE USER_NUM=? "
                  + "AND T_ID=? AND PARENT_ID=0 AND ROWNUM<=? ORDER BY R_DATE DESC) WHERE ?<=RNUM";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uvo.getUserNum());
            pstmt.setInt(2,vo.gettId());
            pstmt.setInt(3, (pageCnt * 10) + 10);
            pstmt.setInt(4, pageCnt * 10);
         }

         else { // 전체 (로그인 안한 상태)
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
        	 TestSet ts = new TestSet();//위치변경!
            rvo = new TestReplyVO(); // 댓글

            // 댓글 다 넣어줌
            rvo.setrId(rs.getInt("R_ID"));
            rvo.settId(rs.getInt("T_ID"));
            rvo.setUserNum(rs.getInt("USER_NUM"));
            rvo.setrContent(rs.getString("R_CONTENT"));
            rvo.setrDate(rs.getDate("R_DATE"));
            rvo.setDeleteAt(rs.getString("DELETE_AT"));
            rvo.setrWriter(rs.getString("CASEWHENDELETE_AT='Y'THEN'UNKNOWN'ELSER_WRITEREND"));
            rvo.setParentId(rs.getInt("PARENT_ID"));

            //rs.close();

          //  System.out.println("reply 확인: " + rvo); // 로깅

            // 대댓글 유무에 따라서
         //   if (rvo.getParentId() > 0) { // 대댓글 존재한다면 // 이 부분 없애야합니다!
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
                  rrlist.add(rrvo); // 댓글 리스트
                  System.out.println("rrlist 확인: " + rrlist);
               }

               rrs.close();

            
            ts.setReply(rvo);
            ts.setRrlist(rrlist);
            datas.add(ts);

            System.out.println("datas 확인: " + datas);

         }
         rs.close();

      } catch (Exception e) {
         System.out.println("TestDAO-selectDBList 오류");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return datas;

   }

/////////////////////////////////////////////////////////////////////////         

   // getDBData
   public TestSet getDBData(TestReplyVO vo) { // testSet으로 리턴
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      TestSet data = null;
      ArrayList<TestReplyVO> rrlist = null;
      TestReplyVO rvo = null;

      String sql;

      try {
         // *기존 일반 -> sql = "SELECT * FROM TEST_REPLY WHERE R_ID=?";

         /*
          * SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE
          * R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY WHERE
          * R_ID=2; ==> SQL문법 : CASE WHEN X=Y THEN A ELSE B END : X=Y가 TRUE면 A이고 그렇지 않으면
          * B다.
          */

         sql = "SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, "
               + "CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, "
               + "PARENT_ID FROM TEST_REPLY WHERE R_ID=?";

         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, vo.getrId());
         ResultSet rs = pstmt.executeQuery();

         if (rs.next()) {
            rvo = new TestReplyVO(); // 댓글

            rvo.setrId(rs.getInt("R_ID"));
            rvo.settId(rs.getInt("T_ID"));
            rvo.setUserNum(rs.getInt("USER_NUM"));
            rvo.setrContent(rs.getString("R_CONTENT"));
            rvo.setrDate(rs.getDate("R_DATE"));
            rvo.setDeleteAt(rs.getString("DELETE_AT"));
            rvo.setrWriter(rs.getString("R_WRITER"));
            rvo.setParentId(rs.getInt("PARENT_ID"));

            rs.close();

            System.out.println("reply 확인: " + rvo); // 로깅

            // 대댓글 유무에 따라서
            if (rvo.getParentId() > 0) { // 대댓글 존재한다면
               sql = "SELECT * FROM TEST_REPLY WHERE PARENT_ID=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, rvo.getrId()); // praent_id == 댓글의 rid

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
                  rrlist.add(rrvo); // 대댓글 리스트

                  System.out.println("rrlist 확인: " + rrlist);
               }
               rrs.close();
            }

            data.setReply(rvo);
            data.setRrlist(rrlist);
         }

      } catch (SQLException e) {
         System.out.println("ReplyDAO-getDBData 오류로깅");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return data;

   }

/////////////////////////////////////////////////////////////////////////         

   // insert --> 댓글 수(RE_CNT) update 트랜잭션!!!!!!★
   public boolean insert(TestReplyVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      boolean check = false; // 트랜잭션 커밋, 롤백 여부 판단 변수

      String sql_INSERT = "INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) "
            + "VALUES ((SELECT NVL(MAX(R_ID),0)+1 FROM TEST_REPLY),?,?,?,?,?)";

      String sql_RECNT_UP = "UPDATE TEST SET RE_CNT= RE_CNT+1 WHERE T_ID=?";
      // 댓글/대댓글이 달리면 특정 TEST게시물의 댓글 수 (RE_CNT) ++

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
         System.out.println("ReplyDAO-insert 오류 로깅");
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

      boolean check = false; // 트랜잭션 커밋, 롤백 여부 판단 변수

      // ** 댓글 삭제인 경우 --> 진짜 삭제가 아닌 ,,, UPDATE 개념 (DELETE_AT = 'N' --> 'Y')
      String sql_DELETE_R1 = "DELETE FROM TEST_REPLY R_ID=?";
      String sql_DELETE_R2 = "UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =? AND PARENT_ID=0";

      // ** 대댓글 삭제인 경우 --> 진짜 삭제
      String sql_DELETE_RR = "DELETE FROM TEST_REPLY WHERE R_ID =? AND PARENT_ID=?";

      // 댓글달린 특정 TEST게시물의 댓글 수 (RE_CNT) --
      String sql_RECNT_DN = "UPDATE TEST SET RE_CNT= RE_CNT-1 WHERE T_ID=?";

      String sql_COUNT = "SELECT COUNT(*) FROM TEST_REPLY WHERE PARENT_ID=?";

      int cnt = 0;

      try {
         if (vo.getParentId() == 0) {

            // 대댓글이 달린 애인지, 대댓글 없는 댓글인지 확인

            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql_COUNT);
            pstmt.setInt(1, vo.getrId());
            ResultSet rs = pstmt.executeQuery();

            cnt = rs.getInt(1);

            if (cnt == 0) { // 대댓글 없는 댓글 삭제 시, -> 완전 삭제
               pstmt = conn.prepareStatement(sql_DELETE_R1); // 완전 DB에서 삭제
               pstmt.setInt(1, vo.getrId());
               pstmt.executeUpdate();
               System.out.println("대댓글 없는 댓글 삭제 완료 로깅");

            } else { // 대댓글이 있는 댓글 삭제 -> DELETE_AT을 "N -> Y"로 변경
               pstmt = conn.prepareStatement(sql_DELETE_R2);
               pstmt.setInt(1, vo.getrId());
               pstmt.executeUpdate();
               System.out.println("대댓글 있는 댓글 삭제 완료 로깅");

            }

         } else { // 삭제 대상이 "대댓글"이라면,
            pstmt = conn.prepareStatement(sql_DELETE_RR);
            pstmt.setInt(1, vo.getrId());
            pstmt.executeUpdate();

         }

         pstmt = conn.prepareStatement(sql_RECNT_DN); // 해당 게시물 댓글수 -1하기 => 댓글, 대댓 삭제 시 게시글 댓글 수 --
         pstmt.setInt(1, vo.gettId());
         pstmt.executeUpdate();

         check = true; // 커밋 하기위해

         if (check) {
            conn.commit();
            res = true;
         } else {
            conn.rollback();
         }

      } catch (SQLException e) {
         System.out.println("ReplyDAO-delete 오류 로깅");
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

      // 댓글 수정
      String sql_UPDATE_R = "UPDATE TEST_REPLY SET T_ID=?, USER_NUM=?, R_CONTENT=?, R_WRITER=?, PARENT_ID=0 WHERE R_ID =?";

      // 대댓글 수정
      String sql_UPDATE_RR = "UPDATE TEST_REPLY SET T_ID=?, USER_NUM=?, R_CONTENT=?,  R_WRITER=?, PARENT_ID=? WHERE R_ID =?";

      try {

         if (vo.getParentId() == 0) { // 수정 대상이 "댓글"이라면,

            pstmt = conn.prepareStatement(sql_UPDATE_R);
            pstmt.setInt(1, vo.gettId());
            pstmt.setInt(2, vo.getUserNum());
            pstmt.setString(3, vo.getrContent());
            pstmt.setString(4, vo.getrWriter());
            pstmt.setInt(5, vo.getrId());
            pstmt.executeUpdate();

            System.out.println("댓글 수정 확인"); // 로깅

         } else { // 삭제 대상이 "대댓글"이라면,

            pstmt = conn.prepareStatement(sql_UPDATE_RR);
            pstmt.setInt(1, vo.gettId());
            pstmt.setInt(2, vo.getUserNum());
            pstmt.setString(3, vo.getrContent());
            pstmt.setString(4, vo.getrWriter());
            pstmt.setInt(5, vo.getParentId());
            pstmt.setInt(6, vo.getrId());
            pstmt.executeUpdate();

            System.out.println("대댓글 수정 확인"); // 로깅
         }

         res = true;

      } catch (SQLException e) {
         System.out.println("ReplyDAO-update 오류 로깅");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }

}
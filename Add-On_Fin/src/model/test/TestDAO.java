package model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.core.JniLifecycleListener;

import model.common.JNDI;
import model.users.UsersVO;

/* TEST ���̺� �÷����Դϴ�.
 *   t_id int primary key,
   user_num int not null,
   t_title varchar(100) not null,
   t_content varchar(4000) not null,
   t_answer varchar(4000) not null,
   T_EX VARCHAR(225) NOT NULL,
   t_writer varchar(50) not null,
   t_date date default sysdate,
   t_hit int default 0,
   t_lang varchar(20) not null,
   RE_CNT int default 0,
   constraint user_num_cons foreign key (user_num) references users(user_num) on delete cascade
 * */

public class TestDAO {

   // ���� ��ġ����

   // static String sql_SELECT_ALL = "SELECT * FROM TEST WHERE"+ condition+ ORDER
   // BY T_DATE DESC";
   // static String sql_SELECT_ONE = "SELECT * FROM TEST WHERE USER_NUM=?";

   static int pageSize = 2; // ����¡ ���� ����

   // getDBList --> �˻���ɱ���
   public ArrayList<TestVO> getDBList(String content, int pageCnt, UsersVO uvo) {
      Connection conn = JNDI.getConnection();
      ArrayList<TestVO> datas = new ArrayList<TestVO>();
      PreparedStatement pstmt = null;
      String sql;

      System.out.println("getDBList ����");

      try { // �α��� �Ѱ��
         if (uvo.getUserNum() > 0) {
            sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, TEST.* FROM TEST WHERE USER_NUM=? AND ROWNUM<=? "
                  + "ORDER BY T_DATE DESC) WHERE ?<=RNUM AND T_title LIKE '%" + content + "%'";
            System.out.println("�α��� �Ѱ��");
            /*
             * SELECT * FROM (SELECT ROWNUM AS RNUM, TEST.* FROM TEST WHERE USER_NUM=1 AND
             * ROWNUM<=5 ORDER BY T_DATE DESC) WHERE 1<=RNUM AND T_title LIKE '%��%';
             */
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uvo.getUserNum());
            pstmt.setInt(2, (pageCnt * pageSize) + pageSize);
            pstmt.setInt(3, pageCnt * pageSize);

         } else {
//            sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, TEST.* FROM TEST WHERE ROWNUM<=? "
//                  + "ORDER BY T_DATE DESC) WHERE ?<=RNUM AND T_title LIKE '%" + content + "%'";
          
        	 sql= "select * from (select a.*, rownum as rnum from(select * from TEST where T_TITLE LIKE '%"+content +"%' order by t_date desc) a) where (rnum <=? and rnum >=?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (pageCnt * pageSize) + pageSize);
            pstmt.setInt(2, pageCnt * pageSize+1);
         }
         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {
            System.out.println("while�� ���� ");
            TestVO data = new TestVO();
            data.settId(rs.getInt("T_ID"));
            data.setUserNum(rs.getInt("USER_NUM"));
            data.settTitle(rs.getString("T_TITLE"));
            data.settContent(rs.getString("T_CONTENT"));
            data.settAnswer(rs.getString("T_ANSWER"));
            data.settEx(rs.getString("T_EX"));
            data.settWriter(rs.getString("T_WRITER"));
            data.settDate(rs.getDate("T_DATE"));
            data.settHit(rs.getInt("T_HIT"));
            data.settLang(rs.getString("T_LANG"));
            data.setReCnt(rs.getInt("RE_CNT"));
            datas.add(data);
            System.out.println("datas : " + datas);
         }
         rs.close();

      } catch (SQLException e) {
         System.out.println("TestDAO-getDBList ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      System.out.println("���� ����");
      return datas;

   }
/////////////////////////////////////////////////////////////////////////   

   // getDBData
   public TestVO getDBData( TestVO vo) {
      Connection conn = JNDI.getConnection();
      TestVO data = null;
      PreparedStatement pstmt = null;

      try {

         String sql_SELECT_ONE = "SELECT * FROM TEST WHERE T_ID=?";
         pstmt = conn.prepareStatement(sql_SELECT_ONE);
         pstmt.setInt(1, vo.gettId());
         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {
            data = new TestVO();
            data.settId(rs.getInt("T_ID"));
            data.setUserNum(rs.getInt("USER_NUM"));
            data.settTitle(rs.getString("T_TITLE"));
            data.settContent(rs.getString("T_CONTENT"));
            data.settAnswer(rs.getString("T_ANSWER"));
            data.settEx(rs.getString("T_EX"));
            data.settWriter(rs.getString("T_WRITER"));
            data.settDate(rs.getDate("T_DATE"));
            data.settHit(rs.getInt("T_HIT"));
            data.settLang(rs.getString("T_LANG"));
            data.setReCnt(rs.getInt("RE_CNT"));
         }
         rs.close();

      } catch (SQLException e) {
         System.out.println("TestDAO-getDBData �����α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return data;
   }
/////////////////////////////////////////////////////////////////////////   

   public boolean insert(TestVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      String sql_INSERT = "INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER, T_EX, T_WRITER, T_LANG) "
            + "VALUES ((SELECT NVL(MAX(T_ID),0)+1 FROM TEST),?,?,?,?,?,?,?)";

      try {
         pstmt = conn.prepareStatement(sql_INSERT);

         pstmt.setInt(1, vo.getUserNum());
         pstmt.setString(2, vo.gettTitle());
         pstmt.setString(3, vo.gettContent());
         pstmt.setString(4, vo.gettAnswer());
         pstmt.setString(5, vo.gettEx());
         pstmt.setString(6, vo.gettWriter());
         pstmt.setString(7, vo.gettLang());
         pstmt.executeUpdate();
         res= true;
      } catch (SQLException e) {
         System.out.println("TestDAO-insert ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }
/////////////////////////////////////////////////////////////////////////

   // update
   public boolean update(TestVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      String sql_UPDATE = "UPDATE TEST SET T_TITLE=?, T_CONTENT=?, T_ANSWER=?, T_EX=?,  "
            + " T_LANG=? WHERE T_ID=?";

      try {
         pstmt = conn.prepareStatement(sql_UPDATE);
        
         pstmt.setString(1, vo.gettTitle());
         pstmt.setString(2, vo.gettContent());
         pstmt.setString(3, vo.gettAnswer());
         pstmt.setString(4, vo.gettEx());
      
   
         pstmt.setString(5, vo.gettLang());
       
         pstmt.setInt(6, vo.gettId());
         pstmt.executeUpdate();
         res = true;
      } catch (SQLException e) {
         System.out.println("TestDAO-update ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }
/////////////////////////////////////////////////////////////////////////

   // delete
   public boolean delete(TestVO vo) {
      Connection conn = JNDI.getConnection();
      boolean res = false;
      PreparedStatement pstmt = null;

      String sql_DELETE = "DELETE FROM TEST WHERE T_ID =?";

      try {
         pstmt = conn.prepareStatement(sql_DELETE);
         pstmt.setInt(1, vo.gettId());
         pstmt.executeUpdate();
         res = true;
      } catch (SQLException e) {
         System.out.println("TestDAO-delete ���� �α�");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }

}
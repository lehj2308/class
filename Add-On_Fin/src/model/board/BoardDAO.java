package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;
import model.users.UsersVO;

public class BoardDAO {

   // 게시글 새로 작성 
   // 입력 값 : (B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) 
   // DEFAULT 자동 입력값(B_DATE, B_HIT, RE_CNT)
   static String sql_INSERT = "INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) "
         + "VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)";
   // 게시글 삭제
   static String sql_DELETE = "DELETE FROM BOARD WHERE B_ID=?";
   // 게시글 업데이트 
   // 변경가능 정보 : (카테고리, 타이틀, 컨텐츠(게시글 내용), 프로그래밍 언어) 
   // 변경불가 : (B_ID, USER_NUM, 작성자,작성일, 댓글수, 조회수)
   static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_ID=?"; // B_NUM 오타있었음!
   // 유저 ID 값 게시글 전체출력
   //static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //더보기식 페이징
   // 숫자식 페이징
   static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
         + "BOARD.* FROM BOARD WHERE USER_NUM=? AND ROWNUM<=? ORDER BY B_DATE DESC) WHERE ?<=RNUM AND B_TITLE LIKE '%'||?||'%'";

   // 게시글 전체출력
   static String sql_SELECT_ALL = "select * from (select a.*, rownum as rnum from(select * from board where b_ctgr = ? and B_TITLE LIKE '%' || ? || '%' order by b_date desc) a) where (rnum <=? and rnum >=?) ";
 // 오타있었음! BDATE
   // 게시글 하나 출력
   static String sql_SELECT_ONE = "SELECT * FROM BOARD WHERE B_ID=?";  // 오타있었음
   //=====================================================================================
   // selectAll BoardVO가 들어있는 datas 반환

   static int pageSize = 2; // 페이지 10개씩 출력 출력갯수 바꾸실때 여기 바꾸시면 됩니다.

   // userNum 값 없을 때 0넣어주세요
   public ArrayList<BoardVO> getDBList(UsersVO uvo, BoardVO vo, int pageNum) {
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
      System.out.println("boardDAO getDBList에서 실행");
      try {
         // 로그인 아이디가 있다면 (내 글보기)
         if (uvo.getUserNum() > 0) {
            pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
            pstmt.setInt(1, uvo.getUserNum());
            pstmt.setInt(3, (pageNum*pageSize)+pageSize);    // max 번호
            pstmt.setInt(4, pageNum*pageSize);       // start 번호
            pstmt.setString(4, vo.getbContent());
       
         }
         // 전체 출력
         else {
        	 System.out.println("카테고리 : "+ vo.getbCtgr());
        	
            pstmt = conn.prepareStatement(sql_SELECT_ALL);
            pstmt.setString(1, vo.getbCtgr());
            pstmt.setInt(3, (pageNum*pageSize)+pageSize);    // max 번호
            pstmt.setInt(4, pageNum*pageSize+1);       // start 번호
            pstmt.setString(2,vo.getbTitle());
         }

         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
        	// System.out.println("확인");
            BoardVO bvo = new BoardVO();

            bvo.setbId(rs.getInt("B_ID"));            // 게시글 번호
            bvo.setUserNum(rs.getInt("USER_NUM"));       // 작성 유저 번호
            bvo.setbCtgr(rs.getString("B_CTGR"));       // 게시글 카테고리
            bvo.setbTitle(rs.getString("B_TITLE"));      // 게시글 제목
            bvo.setbContent(rs.getString("B_CONTENT")); // 게시글 내용
            bvo.setbWriter(rs.getString("B_WRITER"));    // 글쓴이
            bvo.setbDate(rs.getDate("B_DATE"));       // 작성일
            bvo.setbHit(rs.getInt("B_HIT"));          // 조회수
            bvo.setbLang(rs.getString("B_LANG"));       // 프로그래밍 언어
            bvo.setReCnt(rs.getInt("B_COUNT"));          // 댓글 수 

            datas.add(bvo);
         }
         rs.close();
      } catch(SQLException e) {
         System.out.println("boardDAO getDBList에서 발생");
         e.printStackTrace();
      }
      return datas;
   }
   //=====================================================================================
   public BoardVO getDBData(BoardVO vo) {
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      BoardVO bvo = null;  // data 변수 필요 없어서 삭제했습니다!.
      try {
         pstmt = conn.prepareStatement(sql_SELECT_ONE);
         pstmt.setInt(1, vo.getbId());

         ResultSet rs = pstmt.executeQuery();

         if(rs.next()) {
        	System.out.println("확인");
            bvo = new BoardVO();

            bvo.setbId(rs.getInt("B_ID"));            // 게시글 번호
            bvo.setUserNum(rs.getInt("USER_NUM"));       // 작성 유저 번호
            bvo.setbCtgr(rs.getString("B_CTGR"));       // 게시글 카테고리
            bvo.setbTitle(rs.getString("B_TITLE"));      // 게시글 제목
            bvo.setbContent(rs.getString("B_CONTENT")); // 게시글 내용
            bvo.setbWriter(rs.getString("B_WRITER"));    // 글쓴이
            bvo.setbDate(rs.getDate("B_DATE"));       // 작성일
            bvo.setbHit(rs.getInt("B_HIT"));          // 조회수
            bvo.setbLang(rs.getString("B_LANG"));       // 프로그래밍 언어
            bvo.setReCnt(rs.getInt("B_COUNT"));          // 댓글 수  // 오타있었음

         }
         rs.close();
      } catch (SQLException e) {
         System.out.println("BoardDAO getDBData에서 발생");
         e.printStackTrace();
      }
      return bvo; // 수정했습니다!
   }
   //=====================================================================================
   public boolean insert(BoardVO vo) {
      Connection conn = JNDI.getConnection();
      PreparedStatement pstmt = null;
      boolean res = false;
      try {
         // (SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),?,?,?,?,?,?)
         // ? 입력 값 : (USER_NUM, B_CTGR, B-TITLE, B_CONTENT, B_WRITER, B_LANG) 
         pstmt = conn.prepareStatement(sql_INSERT);
         pstmt.setInt(1, vo.getUserNum());       // 유저넘버
         pstmt.setString(2, vo.getbCtgr());      // 카테고리
         pstmt.setString(3, vo.getbTitle());      // 타이틀
         pstmt.setString(4, vo.getbContent());   // 내용
         pstmt.setString(5, vo.getbWriter());   // 글쓴이
         pstmt.setString(6, vo.getbLang());      // 프로그래밍 언어
         pstmt.executeUpdate();
         res=true;
      } catch(SQLException e) {
         System.out.println("BoardDAO insert에서 발생");
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
         // 변경가능 정보 : (카테고리, 타이틀, 컨텐츠(게시글 내용), 프로그래밍 언어) 
         // 변경불가 : (B_ID, USER_NUM, 작성자,작성일, 댓글수, 조회수)
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
         System.out.println("BoardDAO update에서 발생");
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
         pstmt.setInt(1, vo.getbId());   // 아이디 값으로 삭제
         pstmt.executeUpdate();
         res = true;
      } catch (SQLException e) {
         System.out.println("BoardDAO delete에서 발생");
         e.printStackTrace();
      } finally {
         JNDI.disconnect(pstmt, conn);
      }
      return res;
   }


}
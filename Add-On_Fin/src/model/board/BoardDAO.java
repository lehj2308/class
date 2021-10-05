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
	static String sql_UPDATE = "UPDATE BOARD SET B_CTGR=?, B_TITLE=?, B_CONTENT=?, B_LANG=? WHERE B_ID=?";
	// 유저 ID 값 게시글 전체출력
	//static String sql_SELECT_ALL_USER = "SELECT * FROM BOARD (SELECT * FROM BOARD ORDER BY BDATE DESC) WHERE USER_NUM=? AND ROWNUM <=?"; //더보기식 페이징

	// 숫자식 페이징
	/*static String sql_SELECT_ALL_USER = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
			+ "BOARD.* FROM BOARD WHERE USER_NUM=? AND ROWNUM<=? ORDER BY ? DESC, B_ID DESC) WHERE ?<RNUM AND B_TITLE LIKE '%'||?||'%'";*/
	static String sql_SELECT_ALL_USER;

	// 가장 최신 selectAll_USER ******
	/*static String sql_SELECT_ALL_USER = "SELECT * FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
			+ "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
			+ ") BOARD WHERE ROWNUM <= ?" //끝점
			+ ") WHERE RNUM > ? ORDER BY ? DESC, B_DATE DESC";*/

	static String sql_SELECT_ALL;

	// 가장 최신 selectAll ******
	/*static String sql_SELECT_ALL = "SELECT * FROM ("
			+ "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
			+ "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
			+ ") board WHERE ROWNUM <= ?" //끝점
			+ ") WHERE RNUM > ? ORDER BY RE_CNT DESC, B_DATE DESC";*/

	// 게시글 전체출력
	/*static String sql_SELECT_ALL = "SELECT * FROM (SELECT ROWNUM AS RNUM, "
			+ "BOARD.* FROM BOARD WHERE B_CTGR=? AND ROWNUM<=? ORDER BY ? DESC, B_ID DESC) WHERE ?<RNUM AND B_TITLE LIKE '%'||?||'%'";*/

	// 게시글 하나 출력
	static String sql_SELECT_ONE = "SELECT * FROM BOARD WHERE B_ID=?";
	//=====================================================================================
	// selectAll BoardVO가 들어있는 datas 반환

	static int pageSize = 3; // 페이지 10개씩 출력 출력갯수 바꾸실때 여기 바꾸시면 됩니다.

	// userNum 값 없을 때 0넣어주세요
	
	 @SuppressWarnings("resource")
	   public BoardSet getDBList(UsersVO uvo, BoardVO vo, String pageOrder, int pageNum) {
	      Connection conn = JNDI.getConnection();
	      PreparedStatement pstmt = null;
	      BoardSet datas = new BoardSet();
	      // 게시글을 담은 리스트 + 전체 게시물 cnt;
	      String sql;
	      int cnt=0;

	      int startNum = pageNum*pageSize;
	      int lastNum = (pageNum*pageSize)+pageSize;

	      // System.out.println(uvo);
	      System.out.println(vo);

	      try {
	         // 로그인 아이디가 있다면 (내 글보기)
	         if (uvo.getUserNum() > 0) {
	            if(pageOrder.equals("reply")) {
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR =? ORDER BY RE_CNT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY RE_CNT DESC";
	               System.out.println("댓글순 통과");
	            }
	            else if(pageOrder.equals("hit")){
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR = ? ORDER BY B_HIT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY B_HIT DESC";
	               System.out.println("조회순 통과");
	            }
	            else {
	               sql_SELECT_ALL_USER = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE USER_NUM=? AND B_TITLE LIKE '%'||?||'%' AND B_CTGR = ?ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY '%' DESC";
	               System.out.println("전체 통과");
	            }


	            pstmt = conn.prepareStatement(sql_SELECT_ALL_USER);
	            pstmt.setInt(1, uvo.getUserNum());
	            pstmt.setString(2, vo.getbTitle());
	            pstmt.setString(3, vo.getbCtgr());
	            pstmt.setInt(4, lastNum);    // max 번호
	            pstmt.setInt(5, startNum);       // start 번호

	         }
	         // 전체 출력
	         else {

	            if(pageOrder.equals("reply")) {
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY RE_CNT DESC, B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY RE_CNT DESC";
	               System.out.println("댓글순 통과");
	            }
	            else if(pageOrder.equals("hit")){
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY B_HIT DESC";
	               System.out.println("조회순 통과");
	            }
	            else {
	               sql_SELECT_ALL = "SELECT * FROM ("
	                     + "SELECT ROWNUM AS RNUM, BOARD.* FROM ("
	                     + "SELECT * FROM BOARD WHERE B_CTGR = ? AND B_TITLE LIKE '%'||?||'%' ORDER BY B_DATE DESC"
	                     + ") BOARD WHERE ROWNUM <= ?" //끝점
	                     + ") WHERE RNUM > ? ORDER BY B_DATE DESC";
	               System.out.println("전체 통과");
	            }

	            pstmt = conn.prepareStatement(sql_SELECT_ALL);
	            pstmt.setString(1, vo.getbCtgr());
	            pstmt.setString(2, vo.getbTitle());
	            pstmt.setInt(3, lastNum);    // max 번호
	            /*if(pageOrder.equals("댓글순")) {
	               pstmt.setString(3, "RE_CNT");
	               System.out.println("댓글순 통과");
	            }
	            else if(pageOrder.equals("조회순")){
	               pstmt.setString(3, "B_HIT");
	               System.out.println("조회순 통과");
	            }
	            else {
	               pstmt.setString(3, "'%'");
	               System.out.println("전체 통과");
	            }*/
	            pstmt.setInt(4, startNum);       // start 번호
	         }

	         ResultSet rs = pstmt.executeQuery();
	         BoardVO bvo = null;
	         ArrayList<BoardVO>blist = new ArrayList<BoardVO>();

	         while(rs.next()) {
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
	            bvo.setReCnt(rs.getInt("RE_CNT"));          // 댓글 수 

	            blist.add(bvo);
	            System.out.println("bvo 확인 : "+bvo);
	            
	         }
	         rs.close();

	         // System.out.println("blist 확인 : "+blist);

	         if(uvo.getUserNum() > 0) {
	            // 내가 쓴 글 전체 개수 출력
	            sql = "SELECT COUNT(*) FROM BOARD WHERE B_CTGR=? AND USER_NUM=? AND B_TITLE LIKE '%'||?||'%'";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, vo.getbCtgr());
	            pstmt.setInt(2, uvo.getUserNum());
	            pstmt.setString(3, vo.getbTitle());
	         }
	         else {
	            // 카테고리별 전체 개수 출력
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
	         System.out.println("boardDAO getDBList에서 발생");
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

				data.setbId(rs.getInt("B_ID"));				// 게시글 번호
				data.setUserNum(rs.getInt("USER_NUM")); 		// 작성 유저 번호
				data.setbCtgr(rs.getString("B_CTGR")); 		// 게시글 카테고리
				data.setbTitle(rs.getString("B_TITLE"));		// 게시글 제목
				data.setbContent(rs.getString("B_CONTENT")); // 게시글 내용
				data.setbWriter(rs.getString("B_WRITER")); 	// 글쓴이
				data.setbDate(rs.getDate("B_DATE")); 		// 작성일
				data.setbHit(rs.getInt("B_HIT")); 			// 조회수
				data.setbLang(rs.getString("B_LANG")); 		// 프로그래밍 언어
				data.setReCnt(rs.getInt("RE_CNT")); 			// 댓글 수 
				
				// System.out.println(data);
			}
			rs.close();

			// 내가 쓴 글이면 조회수 증가 안함
			if(uvo.getUserNum() == data.getUserNum()) {
				System.out.println("uvo.getUserNum() : "+data.getUserNum() );
				System.out.println("bvo.getUserNum() : "+data.getUserNum() );
				System.out.println("BoardDAO.getdbDATe 조회수 증가 x");
				check = true;
			}
			else {
				System.out.println("BoardDAO.getdbDATe 조회수 증가 o");
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
			System.out.println("BoardDAO getDBData에서 발생");
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
			// ? 입력 값 : (USER_NUM, B_CTGR, B-TITLE, B_CONTENT, B_WRITER, B_LANG) 
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setInt(1, vo.getUserNum()); 		// 유저넘버
			pstmt.setString(2, vo.getbCtgr());		// 카테고리
			pstmt.setString(3, vo.getbTitle());		// 타이틀
			pstmt.setString(4, vo.getbContent());	// 내용
			pstmt.setString(5, vo.getbWriter());	// 글쓴이
			pstmt.setString(6, vo.getbLang());		// 프로그래밍 언어
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
			pstmt.setInt(1, vo.getbId());	// 아이디 값으로 삭제
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
	//=====================================================================================
	// 카테고리별 게시글 갯수
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
			System.out.println("BoardDAO boardCnt에서 발생");
			e.printStackTrace();
		}
		return cnt;
	}*/
}


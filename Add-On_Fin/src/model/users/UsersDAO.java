package model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.common.JNDI;
import model.test.TestReplyVO;
import model.test.TestVO;

/* 회원 테이블
 * user_num int primary key,
user_name varchar(15) not null,
user_id varchar(50) not null,
user_pw varchar(50) not null,
user_hp varchar(25) not null,
user_gender varchar(5) not null,
user_email varchar(225) not null,
user_addr varchar(225) not null,
user_birth varchar(30) not null,
icon_id varchar(30) not null
 * */

public class UsersDAO {

	// getDBList 
	public ArrayList<UsersVO> getDBList() {
		Connection conn = JNDI.getConnection();
		ArrayList<UsersVO> datas = new ArrayList<UsersVO>();
		PreparedStatement pstmt = null;

		String sql_SELECT_ALL = "SELECT * FROM USERS ORDER BY USER_NUM DESC";
		// 최신 회원가입한 순서대로 가져옴 

		try {

			pstmt = conn.prepareStatement(sql_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				UsersVO data = new UsersVO();
				data.setAddr(rs.getString("USER_ADDR"));
				data.setBirth(rs.getString("USER_BIRTH"));
				data.setEmail(rs.getString("USER_EMAIL"));
				data.setGender(rs.getString("USER_GENDER"));
				data.setIconId(rs.getString("ICON_ID"));
				data.setId(rs.getString("USER_ID"));
				data.setName(rs.getString("USER_NAME"));
				data.setPhone(rs.getNString("USER_HP"));
				data.setPw(rs.getString("USER_PW"));
				data.setUserNum(rs.getInt("USER_NUM"));
				datas.add(data);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("UsersDAO-getDBList 오류 로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;

	}
	/////////////////////////////////////////////////////////////////////////

	// getDBData
	public UsersVO getDBData(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		UsersVO data = null;
		PreparedStatement pstmt = null;

		//String sql_SELECT_ONE = "SELECT * FROM USERS WHERE USER_NUM=?"; //수정했음
		String sql_SELECT_ONE = "SELECT * FROM USERS WHERE USER_ID=?";
		try {
			pstmt = conn.prepareStatement(sql_SELECT_ONE);
			//pstmt.setInt(1, vo.getUserNum());
			pstmt.setString(1, vo.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				data = new UsersVO();
				data.setAddr(rs.getString("USER_ADDR"));
				data.setBirth(rs.getString("USER_BIRTH"));
				data.setEmail(rs.getString("USER_EMAIL"));
				data.setGender(rs.getString("USER_GENDER"));
				data.setIconId(rs.getString("ICON_ID"));
				data.setId(rs.getString("USER_ID"));
				data.setUserNum(rs.getInt("USER_NUM"));
				data.setName(rs.getString("USER_NAME"));
				data.setPhone(rs.getNString("USER_HP"));
				data.setPw(rs.getString("USER_PW"));
				data.setUserNum(rs.getInt("USER_NUM"));
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("UsersDAO-getDBData 오류로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;

	}

	/////////////////////////////////////////////////////////////////////////

	// insert
	public boolean insert(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;

		boolean res = false;

		// insert into USERS values((select NVL(MAX(user_num),0)+1 from users), '박정은',
		// 'je','111','01011111111','F','je@gmail.com','주소','생일','1');
		String sql_INSERT = "INSERT INTO USERS VALUES ((SELECT NVL(MAX(USER_NUM),0)+1 FROM USERS),?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getBirth());
			pstmt.setString(9, vo.getIconId());
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("UsersDAO-insert 오류로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;

	}
	/////////////////////////////////////////////////////////////////////////
	// update
	public boolean update(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;

		boolean res = false;

		String sql_UPDATE = "UPDATE USERS SET USER_NAME=?, USER_ID=?, USER_PW=?, USER_HP=?, USER_GENDER=?, "
				+ "USER_EMAIL=?, USER_ADDR=?, USER_BIRTH=?, ICON_ID=? WHERE USER_NUM=?";
		try {
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getBirth());
			pstmt.setString(9, vo.getIconId());
			pstmt.setInt(10, vo.getUserNum());
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("UsersDAO-update 오류로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;

	}
	/////////////////////////////////////////////////////////////////////////
	// delete
	public boolean delete(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		PreparedStatement pstmt = null;

		boolean res = false;

		String sql_DELETE = "DELETE FROM USERS WHERE USER_NUM=?";
		try {
			pstmt = conn.prepareStatement(sql_DELETE);
			pstmt.setInt(1, vo.getUserNum());
			pstmt.executeUpdate();
			res = true;
		} catch (SQLException e) {
			System.out.println("UsersDAO-delete 오류로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;

	}
	/////////////////////////////////////////////////////////////////////////
	// login
	public boolean login(UsersVO vo) {
		Connection conn = JNDI.getConnection();
		UsersVO data = null;
		PreparedStatement pstmt = null;

		String sql_LOGIN = "SELECT USER_ID, USER_PW FROM USERS WHERE USER_ID=?";
		boolean res = false;

		try {
			pstmt = conn.prepareStatement(sql_LOGIN);
			pstmt.setString(1, vo.getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {   // rs.next() 값이 없을 경우 에러 발생하여 수정했음 
				System.out.println(rs.getString("USER_PW"));
				if (rs.getString("USER_PW").equals(vo.getPw())) {
					res = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("UserDAO-login 오류 로깅");
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return res;

	}

}

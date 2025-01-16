package com.ariel.jdbc.day02.stmt.member.model.dao;

// * 해두면 전체 import 자동으로 돼서 안해줘도 됨
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ariel.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "STUDENT2";
	private static final String PASSWORD = "STUDENT2";

	/*
	 * 여기서 JDBC코딩 할거임
	 * 1. 드라이버 등록
	 * 2. DBMS연결 생성
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	// 회원 가입
	public int insertMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, AGE)" + "VALUES('"+member.getMemberId()+"', '"+member.getMemberPwd()+"', '"+member.getMemberName()+"', "+member.getAge()+")";

		int result = 0;
		Connection conn = null;
		Statement stmt = null;
			try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query); //여기서 예외가 발생하면 close() 코드는 실행되지 않음.

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 예외가 발생하든 안하든 실행문을 동작시켜줌.
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	} 
	// 회원정보 수정
	public int updateMember(Member member) {
		int result = 0;
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPwd()
															+"', EMAIL = '"+member.getEmail()
															+"', PHONE = '"+member.getPhone()
															+"', ADDRESS='"+member.getAddress()
															+"', HOBBY = '"+member.getHobby()
															+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn =this.getConnection();
			stmt = conn.createStatement();
			// 쿼리문 실행 코드 누락 주의!!
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	public int deleteMember(String memberId) {
		int result = 0;
		String query = "DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			// Statement 생성
			stmt = conn.createStatement();
			// 쿼리문 실행
			result = stmt.executeUpdate(query);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		// 성공여부를 알 수 있도록 result를 리턴해줌
		return result;
	}

	public List<Member> selectList() {
		List<Member> mList = new ArrayList<Member>();
		String query = "SELECT * FROM MEMBER_TBL";

		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				
				Member member = this.rsetToMember(rset);
				
				// while문이 동작하면서 모든 레코드에 대한 정보를
				// mList에 담을 수 있도록 add 해줌
				mList.add(member);
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 리턴을 null로 하면 nullPointerException 발생
		// mList로 리턴해주어야 함.
		return mList;
	}
	// 회원 아이디로 검색
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'"; // 고정된 거면  '' 로만 하고 변수로 쓰고 싶으면 "" 로 감싸줌. 그 다음에 +로 연결해줌. 이게 없으면 문자열이 될 수 없어. 변수화해준것. 
		Member member = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				// rset은 member로 변환되어야 함(rsetToMember)
				member = this.rsetToMember(rset);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		String memberId 	 = rset.getString("MEMBER_ID");
		String memberPwd 	 = rset.getString("MEMBER_PWD");
		String memberName 	 = rset.getString("MEMBER_NAME");
		String gender 	 	 = rset.getString("GENDER");
		int age 	 		 = rset.getInt("AGE");
		String email 		 = rset.getString("EMAIL");
		String phone 		 = rset.getString("PHONE");
		String address 		 = rset.getString("ADDRESS");
		String hobby 		 = rset.getString("HOBBY");
		Date enrollDate 	 = rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
		return member;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

}

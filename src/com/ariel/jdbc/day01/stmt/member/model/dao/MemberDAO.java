package com.ariel.jdbc.day01.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ariel.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {

	private static final String DRIVER_NAME = null;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";
	
	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL";
		// 최종적으로 리턴해줘야 하는 객체
		List<Member> mList = new ArrayList<Member>();
		Member member = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			// rset을 그대로 사용할 수 없어서
			// member로 변환해주는 작업을 해야함 (rsetToMember)
			
			// 후처리
			// while문을 돌면서 resultset의 모든 레코드가
			// mList로 변환됨.
			while(rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberPwd = rset.getString("MEMBER_NAME");
				char gender = rset.getInt("AGE");
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String address = rset.getString("HOBBY");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				member = new Member(memberId, memberPwd, memberPwd, gender, gender, email, phone, address, address, enrollDate);
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return member;
//				Member member = new Member();
				System.out.println("ID : " + rset.getString("MEMBER_ID"));
				// rset 객체를 추가해줄 수 없으므로 member객체를 추가
				// 그런데 Member member = new Member(); 는 아무 데이터 없이
				// 객체 생성된 상태이고 Member 클래스는 아직 작성하지 않음.
				// rsetToMember: rset 객체에 있는 필드값을 Member로 Converting 필요
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char   gender	= rset.getString("GENDER").charAt(0); //하나자르기
				int	   age		= rset.getInt("AGE");
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String address = rset.getString("ADDRESS");
				String hobby = rset.getString("HOBBY");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				Member member = new Member(
						memberId, memberPwd, memberName
						, gender, age, email, phone
						, address, hobby, enrollDate);
				mList.add(member);
		}
	} catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	// 모든 정보를 담은 mList를 리턴해준다.	
		return mList;
	}

	public int insertMember(Member member) {
		// DML JDBC   //문제 나오기 넘 좋은 곳. 
		String s = "INSERT INTO MEMBER_TBL VALUES('','','','', ,'','','','',DEFAULT)";
		String query = "INSERT INTO MEMBER_TBL VALUES('"
										+member.getMemberId()+"','"+member.getMemberPwd()+"', '"+member.getMemberName()+ "','"
										+member.getGender()+"',"+member.getAge()+ ",'"+member.getEmail()+ "','" 
										+member.getPhone()+ "','"+member.getAddress()+ "','"+ member.getHobby()+"', DEFAULT)"; 
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public Member selectOneById(String memberId) {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch
		return null;
	}
}

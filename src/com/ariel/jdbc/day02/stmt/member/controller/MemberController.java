package com.ariel.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.ariel.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.ariel.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {

	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	// 회원가입
	public int insertMember(Member member) {
		int result = mDao.insertMember(member);
		// return result를 해서 view로 결과값을 보내줌
		return result;
	}
	public int deleteMember(String memberId) {
		int result = mDao.deleteMember(memberId);
		return result;
	}
	// 회원 전체 정보 조회
	public List<Member> selectList() {
		List<Member> mList = mDao.selectList();
		// View에서 mList를 사용할 수 있도록 리턴해줌
		return mList;
	}
	// 회원 아이디로 검색
	public Member selectOneById(String memberId) {
//		SELECT * FROM MEMBER2_TBL WHERE MEMBER2_ID = 'memberId';
		Member member = mDao.selectOneById(memberId);
		// member를 View에서 사용할 수 있도록 리턴해줌
		return member;
	}
	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}
	
}

package com.ariel.jdbc.day01.stmt.member.controller;

import java.util.List;

import com.ariel.jdbc.day01.stmt.member.model.dao.MemberDAO;
import com.ariel.jdbc.day01.stmt.member.model.vo.Member;


public class MemberController {
	// 선언만 한 상태 - 널 초기화 
	private MemberDAO mDao;
	
	public MemberController() {
		
		mDao = new MemberDAO();
	}
	
	public Member findOneById(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Member> showMemberList() {
		// 내가 원하는 건 List<Member> 값이고
		// MemberDAO가 가져다 줄거임.
		List<Member>mList = mDao.selectList();
		// MemberDAO 가 잘 수행되었다면 mList에 
		// 데이터가 있을 것이고 컨트롤러에서 
		// 가져다 쓸 수 있도록 리턴을 해주어야 함.
		return mList;
	}

	public int registerMember(Member member) {
		int result = 	mDao.insertMember(member);
		return result;
		
	}

	public Member findOneById(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}

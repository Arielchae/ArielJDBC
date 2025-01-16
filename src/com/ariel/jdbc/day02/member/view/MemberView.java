package com.ariel.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.ariel.jdbc.day02.stmt.member.controller.MemberController;
import com.ariel.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	
	private static final String SUCCESS_MSG = "[서비스 성공]";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String BYE_MSG = "프로그램이 종료되었습니다.";
	private static final String NO_DATA_FOUND = "데이터가 존재하지 않습니다.";
	private Scanner sc;
	private MemberController manage;
	
	public MemberView() {
		sc = new Scanner(System.in);
		manage = new MemberController();
	}
	public void startProgram() {
		끝:
		while(true) {
			int menu = this.showMainMenu();
			switch(menu) {
			case 1: 
				// 회원정보 입력
				Member member = this.inputMember();
				int result = manage.insertMember(member);
				if(result > 0) {
					// 성공
					this.showMessage(SUCCESS_MSG);
				}else {
					// 실패
					this.showMessage(FAIL_MSG);
				}
				break;
			case 2: 
				// 회원 정보 수정
				// 아이디, 비번, 이름, 성멸, 나이, 이메일, 폰, 주소, 취미
				// --> 추리면 -> 비번, 이메일, 폰, 취미 정도 바꿀 수 있게 할 수 있다.
				// UPDATE MEMBER_TBL SET MEMBER_PWD = 'qwer1234', EMAIL = 'khuser01@naver.com'
				// , PHONE = '01019987773', ADDRESS = '경기도 남양주시', HOBBY = '코딩, 수영'
				// WHERE MEMBER_ID = 'user11';
				// - 데이터 있을 경우 수정, 없으면 NO_DATA_FOUND 출력
				String memberId = this.inputMemberId();
				member = manage.selectOneById(memberId);
				if(member != null) {
					// 정보 수정
					member = this.modifyMember(memberId);
//					member.setMemberId(member.geMemberId());
					result = manage.updateMember(member);
					if(result > 0) {
						this.showMessage(SUCCESS_MSG);
					}else {
						this.showMessage(FAIL_MSG);
					}
				}else {
					this.showMessage(NO_DATA_FOUND);
				}
				break;
			case 3: 
				// DELETE FROM MEMBER_TBL WHERE MEMBER_ID = 'user01';
				memberId = this.inputMemberId();
				result = manage.deleteMember(memberId);
				if(result > 0) {
					this.showMessage(SUCCESS_MSG);
				}else {
					this.showMessage(FAIL_MSG);
				}
				break;
			case 4: 
				// 회원 전체 정보 조회
				List<Member> mList = manage.selectList();
				this.viewAllMember(mList);
				break;
			case 5: 
				// SELECT * FROM MEMBER2_TBL WHERE MEMBER2_ID = 'memberId';
				// SELECT를 실행한 결과는 딱 1개 나옴 List<Member>가 아닌
				// Member로 받아야 함.
				// 회원 검색(아이디)
				memberId = this.inputMemberId();
				member = manage.selectOneById(memberId);
				if(member != null) {
					this.viewOneMember(member);
				}else {
					this.showMessage(NO_DATA_FOUND);
				}
				break;
			case 0: 
				this.showMessage(BYE_MSG);
				break 끝;
			default : break;
			}
		}
		
	}

	private Member modifyMember(String meberId) {
		System.out.println("====== 수정할 정보 입력 ======");
		// --> 추리면 -> 비번, 이메일, 폰, 취미 정도 바꿀 수 있게 할 수 있다.
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : "); 
		sc.nextLine(); // 앞에서 입력한 엔터를 제거, 개행제거
		String address = sc.nextLine(); // next()는 띄어쓰기, 개행 입력 불가능(처리x), 주소는 띄어쓰기가 있으므로 nextLine() 사용
		System.out.print("취미 : ");
		String hobby = sc.next();
		// 입력한 데이터를 객체 초기화함. 객체 생성됨
		Member member = new Member(memberPwd, memberPwd, memberPwd, email, address, hobby);
		// 호출한 곳에서 쓸 수 있도록 member 리턴함.
		return member;
	}
	private void viewOneMember(Member member) {
		System.out.println("====== 회원 검색 결과 ======");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()
		+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
	}
	private String inputMemberId() {
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	
	private void viewAllMember(List<Member> mList) {
		System.out.println("====== 회원 정보 출력 ======");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		for(Member member: mList) {
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()
			+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
		}
	}
	
	private void showMessage(String message) {
		System.out.println(message);
		
	}
	private Member inputMember() {
		System.out.println("====== 회원 정보 입력 ======");
		System.out.println("아이디 : ");
		String memberId = sc.next();
		System.out.println("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.println("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M,F) : ");
		String gender = sc.next();
		System.out.println("나이 : ");
		int age = sc.nextInt();
		Member member = new Member(memberId, memberPwd, memberName, age);
		return member;
	}

	private int showMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 회원 정보 조회(ALL)");
		System.out.println("5. 회원 검색(아이디)");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

}

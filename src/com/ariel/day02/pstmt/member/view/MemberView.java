package com.ariel.day02.pstmt.member.view;

public class MemberView {
	
	private static final String SUCCESS_MSG = null;
	private static final String FAIL_MSG = null;

	public void startProgram() {
		while(true) {
			int menu = this.showmainMenu();
			switch(menu) {
			case 1 : 
				// 회원정보 입력
				Member member = this.inputMember();
				int result > 0 {
					// 성공
					this.showMessage(SUCCESS_MSG);
				} else {
					// 실패
					this.showMessage(FAIL_MSG);
				}
				break;
			case 2 : 
				// 회원 정보 수정
				// 하이디, 비번, 추리, 이름, 설명, 나이, 이메일, 폰, 주소, 취미
				break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 0 : break;
			default : break;
			
			}
		
		}
	}
		// 5. 회원 아이디로 검색
		// 해당 기능을 구현해주세요~!



}

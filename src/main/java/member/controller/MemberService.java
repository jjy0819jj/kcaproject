package member.controller;

import java.util.List;


import member.bean.MemberDTO;

public interface MemberService {
	// 회원 가입
		public int MemberJoin(MemberDTO memberDTO);
	// 회원 정보
		public MemberDTO memberView(String email);
	// 회원 목록
		public List<MemberDTO> memberList(int startNum, int endNum);
	// 회원 목록 갯수 구하기
		public int getTotalA();
	// 회원 정보 수정
		public int memberUpdate(MemberDTO memberDTO);
	// 회원 삭제
		public int memberDelete(String email);
	// 파일 업로드
		public int fileUpload(MemberDTO memberDTO);
		
		public int getTotalB();
		public int newUsersList(String day);
		
		public int resumeHit(MemberDTO memberDTO);
		public List<MemberDTO> memberResumeList();
		
}

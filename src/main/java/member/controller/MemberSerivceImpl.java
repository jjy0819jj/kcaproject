package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberSerivceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int MemberJoin(MemberDTO memberDTO) {
		return memberDAO.memberJoin(memberDTO);
	}

	@Override
	public MemberDTO memberView(String email) {
		return memberDAO.memberView(email);
	}


	@Override
	public List<MemberDTO> memberList(int startNum, int endNum) {
		return memberDAO.memberList(startNum, endNum);
	}

	@Override
	public int getTotalA() {
		return memberDAO.getTotalA();
	}

	@Override
	public int memberUpdate(MemberDTO memberDTO) {
		return memberDAO.memberUpdate(memberDTO);
	}

	@Override
	public int memberDelete(String email) {
		return memberDAO.memberDelete(email);
	}

	@Override
	public int fileUpload(MemberDTO memberDTO) {
		return memberDAO.fileUpload(memberDTO);
	}
	
	@Override
	public int getTotalB() {
		return memberDAO.getTotalB();
	}

	@Override
	public int newUsersList(String day) {
		return memberDAO.getTotalnewUsers(day);
	}

	@Override
	public int resumeHit(MemberDTO memberDTO) {
		return memberDAO.resumeHit(memberDTO);
	}

	@Override
	public List<MemberDTO> memberResumeList() {
		return memberDAO.memberResumeList();
	}

}

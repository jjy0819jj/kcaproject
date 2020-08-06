package manager.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.NoticeDTO;
import board.controller.BoardService;
import member.bean.MemberDTO;
import member.controller.MemberService;

@Controller
public class ManagerController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private BoardService boardService;
	
	//0.관리자 페이지 호출
	@RequestMapping(value = "manager.do")
	public ModelAndView manager(HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat ("yyyy.MM.dd");
		SimpleDateFormat format2 = new SimpleDateFormat ("MM.dd");
		ArrayList<Integer> newUsers = new ArrayList<Integer>();
		ArrayList<String> days = new ArrayList<String>();
		int total;
		Date time = new Date();
		for (int i=0 ; i < 7 ; i++) {
			time = new Date();
			time = new Date(time.getTime() - (long)(( 1000 * 60 * 60 * 24 ) * 6));
			time = new Date (time.getTime() + (long)(( 1000 * 60 * 60 * 24 ) * i));
			String day = format.format(time);
			total = memberService.newUsersList(day);
			day = format2.format(time);
			newUsers.add(i, total);
			days.add(i, day);
		}
		
		int totalMember = memberService.getTotalA();
		int totalNotice = boardService.getTotalB(); 
		int totalBoard = boardService.getTotalC();  
		int totalResume = memberService.getTotalB();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("totalMember", totalMember);
		modelAndView.addObject("totalBoard", totalBoard);
		modelAndView.addObject("totalNotice", totalNotice);
		modelAndView.addObject("totalResume", totalResume);
		modelAndView.addObject("list", newUsers);
		modelAndView.addObject("days", days);
		modelAndView.addObject("view", "main");
		modelAndView.setViewName("manager.jsp");
		return modelAndView;  
	}
	
	//1-1.관리자-전체 회원 페이지 호출
	@RequestMapping(value = "memberList.do")
	public ModelAndView managerList(HttpServletRequest request) {
		int pg = Integer.parseInt(request.getParameter("pg"));
		int endNum = pg * 10;
		int startNum = endNum - 9;
		
		List<MemberDTO> list = memberService.memberList(startNum, endNum);
		System.out.println(list);
		
		//paging
		int totalA = memberService.getTotalA();
		int totalP = (totalA + 9) / 10;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("list", list);
		modelAndView.addObject("totalP", totalP);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("view", "memberList");
		modelAndView.addObject("resume", 0);
		modelAndView.setViewName("manager.jsp");
		return modelAndView;  
	}
	
	//1-2.관리자-전체 회원-멤버 수정 페이지 호출
	@RequestMapping(value = "memberUpdateForm.do")
	public ModelAndView memberUpdateForm(HttpServletRequest request) {
		String email = request.getParameter("email");
		
		MemberDTO memberDTO = memberService.memberView(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("email",email);
		modelAndView.addObject("memberDTO",memberDTO);
		modelAndView.setViewName("memberUpdateForm.jsp");
		return modelAndView;
	}
	
	//1-3.관리자-전체 회원-멤버 수정 완료
	@RequestMapping(value = "memberUpdate.do")
	public ModelAndView memberUpdate(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		int member_code =Integer.parseInt(request.getParameter("member_code"));
		String addr = request.getParameter("addr");
		
		// DB
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setPwd(pwd);
		memberDTO.setTel(tel);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setMember_code(member_code);
		memberDTO.setAddr(addr);
		memberDTO.setEmail(email);
		
		int su = memberService.memberUpdate(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("email", email);
		modelAndView.setViewName("memberUpdate.jsp");
		return modelAndView; 
	}
	
	//1-4.관리자-전체 회원-멤버 삭제
	@RequestMapping(value = "memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request) {
		String email = request.getParameter("email"); 
		
		int su = memberService.memberDelete(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("email", email);
		modelAndView.setViewName("memberDelete.jsp");
		return modelAndView;
	}
	
	//1-5.이력서 확인
	@RequestMapping(value = "resumeView.do")
	public ModelAndView resumeHit(HttpServletRequest request) {
		String email = request.getParameter("email");
		String file_name = request.getParameter("file_name");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setFile_name(file_name);
		memberDTO.setEmail(email);
		
		int su = memberService.resumeHit(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("file_name", file_name);
		modelAndView.setViewName("resumeView.jsp");
		return modelAndView;
	}
	
	//2-1.관리자-게시중인 공지사항 페이지 호출
	@RequestMapping(value="managerNoticeList.do")
	public ModelAndView NoticeList(HttpServletRequest request) {
		String str_pg = request.getParameter("pg");
		int pg = 1;
		if (str_pg != null) {
			if (!str_pg.equals("")) {
				if (str_pg.matches("^[0-9]*$")) {
					pg = Integer.parseInt(str_pg);
				}
			}
		}
		
		int endNum = pg * 5;
		int startNum = endNum - 4;
		List<NoticeDTO> notice = boardService.noticeList();
		
		int totalA = boardService.getTotalA();  
		int totalP = (totalA + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		ModelAndView mv = new ModelAndView();
		mv.addObject("pg",pg);
		mv.addObject("list", notice);
		mv.addObject("totalP",totalP);
		mv.addObject("startPage",startPage);
		mv.addObject("endPage",endPage);
		mv.addObject("view", "noticeList");
		mv.setViewName("manager.jsp");
		return mv;
	}
	
	//2-2.관리자-게시중인 공지사항-공지사항 추가
	@RequestMapping(value ="noticeInsert.do")
	public ModelAndView noticeInsert(HttpServletRequest request) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setSubject("새 공지");
		noticeDTO.setContent("내용을 입력하세요.");
		noticeDTO.setRank(0);
		int su = boardService.noticeInsert(noticeDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su", su);
		mv.setViewName("managerNoticeList.do");
		return mv;
	}
	
	//2-3.관리자-게시중인 공지사항-공지사항 삭제
	@RequestMapping(value ="noticeDelete.do")
	public ModelAndView noticeDelete(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		int su = boardService.noticeDelete(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su", su);
		mv.setViewName("managerNoticeList.do");
		return mv;
	}
	
	//2-4.관리자-게시중인 공지사항-공지사항 수정
	@RequestMapping(value ="noticeUpdate.do")
	public ModelAndView noticeUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int rank;
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		String str_rank = request.getParameter("rank");
		if (str_rank.equals("0")) { rank = 0; } 
		else if (str_rank.equals("1")) { rank = 1; } 
		else if (str_rank.equals("2")) { rank = 2; } 
		else { rank = 3; } 
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setSeq(seq);
		noticeDTO.setSubject(subject);
		noticeDTO.setContent(content);
		noticeDTO.setRank(rank);

		int su = boardService.noticeUpdate(noticeDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su", su);
		mv.setViewName("managerNoticeList.do");
		return mv;
	}
	
	//3-1.관리자-답변 대기중 페이지 호출
	@RequestMapping(value="managerBoardList.do")
	public ModelAndView boardList(HttpServletRequest request) {
		String str_pg = request.getParameter("pg");
		int pg = 1;
		if (str_pg != null) {
			if (!str_pg.equals("")) {
				if (str_pg.matches("^[0-9]*$")) {
					pg = Integer.parseInt(str_pg);
				}
			}
		}
		
		int endNum = pg * 5;
		int startNum = endNum - 4;
		List<BoardDTO> list = boardService.boardList(startNum, endNum);
		
		int totalA = boardService.getTotalA();  
		int totalP = (totalA + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		ModelAndView mv = new ModelAndView();
		mv.addObject("pg",pg);
		mv.addObject("list",list);
		mv.addObject("totalP",totalP);
		mv.addObject("startPage",startPage);
		mv.addObject("endPage",endPage);
		mv.addObject("view", "boardList");
		
		mv.setViewName("manager.jsp");
		return mv;
	}
	
	//3-2.관리자-답변 대기중-답변 삭제 호출
	@RequestMapping(value ="boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		int su = boardService.boardDelete(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su", su);
		mv.addObject("pg",pg);
		mv.addObject("view", "boardList");
		mv.setViewName("managerBoardList.do");
		return mv;
	}
	
	//3-3.관리자-답변 대기중-답변 수정 호출
	@RequestMapping(value ="boardUpdate.do")
	public ModelAndView boardUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int pg = Integer.parseInt(request.getParameter("pg"));
		String answer = request.getParameter("answer");
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(seq);
		boardDTO.setAnswer(answer);
		
		int su = boardService.boardUpdate(boardDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su", su);
		mv.addObject("pg",pg);
		mv.addObject("view", "boardList");
		mv.setViewName("managerBoardList.do");
		return mv;
	}
		
	//4.관리자-새 이력서 페이지 호출
	@RequestMapping(value = "memberResumeList.do")
	public ModelAndView managerResumeList(HttpServletRequest request) {
		
		List<MemberDTO> list = memberService.memberResumeList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resume", 1);
		modelAndView.addObject("list", list);
		modelAndView.addObject("view", "memberList");
		modelAndView.setViewName("manager.jsp");
		return modelAndView;  
	}
	
}

package board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.bean.ApplicationDTO;
import board.bean.BoardDTO;
import board.bean.NoticeDTO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="index.do")
	public ModelAndView boardList(HttpServletRequest request) {
		int pg = 1;
		int endNum = pg * 5;
		int startNum = endNum - 4;
		List<NoticeDTO> notice = boardService.noticeList();
		List<BoardDTO> list = boardService.boardList(startNum, endNum);
		int totalA = boardService.getTotalA();  
		int totalP = (totalA + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		ModelAndView mv = new ModelAndView();
		mv.addObject("pg",pg);
		mv.addObject("list",list);
		mv.addObject("notice",notice);
		mv.addObject("totalP",totalP);
		mv.addObject("startPage",startPage);
		mv.addObject("endPage",endPage);
		mv.setViewName("index.jsp");
		return mv;
	}
	
	@RequestMapping(value="board.do")
	public ModelAndView boardList1(HttpServletRequest request) {
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
		List<BoardDTO> list = boardService.boardList(startNum, endNum);
		
		int totalA = boardService.getTotalA();  
		int totalP = (totalA + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		ModelAndView mv = new ModelAndView();
		mv.addObject("pg",pg);
		mv.addObject("list",list);
		mv.addObject("notice",notice);
		mv.addObject("totalP",totalP);
		mv.addObject("startPage",startPage);
		mv.addObject("endPage",endPage);
		mv.addObject("viewPoint", "board");
		mv.setViewName("index.jsp");
		return mv;
	}

	@RequestMapping(value="boardView.do")
	public ModelAndView boardView(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		// DB
		//BoardDAO boardDAO = new BoardDAO();
		boardService.updateHit(seq);
		BoardDTO boardDTO = boardService.boardView(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("seq", seq);
		mv.addObject("pg", pg);
		mv.addObject("boardDTO", boardDTO);
	    mv.setViewName("boardView.jsp");
		return mv;
	}
	
	@RequestMapping(value ="boardWriteForm.do")
	public ModelAndView boardWriteForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardWriteForm.jsp");
		return mv; //viewPage = "/board/boardWriteForm.jsp";
	}
	
	@RequestMapping(value ="boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String question = request.getParameter("question");
		String answer = "�亯�� ��ٸ��� ���Դϴ�.";
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		// DB
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(name);
		boardDTO.setEmail(email);
		boardDTO.setQuestion(question);
		boardDTO.setAnswer(answer);
		
		//BoardDAO boardDAO = new BoardDAO();
		int su = boardService.boardWrite(boardDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("su",su);
		mv.addObject("viewPoint", "board");
		mv.setViewName("index.do");
		return mv;
	}
	
	
	@RequestMapping(value="application.do")
	public ModelAndView boardList3(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
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
		List<ApplicationDTO> application = boardService.applicationList(startNum, endNum);
		System.out.println(application);
		int totalA = boardService.getTotalA();  
		int totalP = (totalA + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("email", email);
		mv.addObject("pg",pg);
		mv.addObject("application", application);
		mv.addObject("totalP",totalP);
		mv.addObject("startPage",startPage);
		mv.addObject("endPage",endPage);
		mv.setViewName("application.jsp");
		return mv;
	}
	
}

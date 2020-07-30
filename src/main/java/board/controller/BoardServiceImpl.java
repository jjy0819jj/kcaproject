package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.ApplicationDTO;
import board.bean.BoardDTO;
import board.bean.NoticeDTO;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int boardWrite(BoardDTO boardDTO) {
		return boardDAO.boardWrite(boardDTO);
	}

	@Override
	public List<BoardDTO> boardList(int startNum, int endNum) {
		return boardDAO.boardList(startNum, endNum);
	}

	@Override
	public BoardDTO boardView(int seq) {
		return boardDAO.boardView(seq);
	}

	@Override
	public int updateHit(int seq) {
		return boardDAO.updateHit(seq);
	}

	@Override
	public int getTotalA() {
		return boardDAO.getTotalA();
	}

	@Override
	public int boardDelete(int seq) {
		return boardDAO.boardDelete(seq);
	}

	@Override
	public List<NoticeDTO> noticeList() {
		return boardDAO.noticeList();
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) {
		return boardDAO.boardUpdate(boardDTO);
	}

	@Override
	public int noticeInsert(NoticeDTO noticeDTO) {
		return boardDAO.noticeWrite(noticeDTO);
	}

	@Override
	public int noticeDelete(int seq) {
		return boardDAO.noticeDelete(seq);
	}

	@Override
	public int noticeUpdate(NoticeDTO noticeDTO) {
		return boardDAO.noticeUpdate(noticeDTO);
	}

	@Override
	public List<ApplicationDTO> applicationList(int startNum, int endNum) {
		return boardDAO.applicationList(startNum, endNum);
	}
	
	@Override
	public int getTotalB() {
		return boardDAO.getTotalB();
	}

	@Override
	public int getTotalC() {
		return boardDAO.getTotalC();
	}
}

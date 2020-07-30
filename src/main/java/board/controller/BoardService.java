package board.controller;

import java.util.List;

import board.bean.ApplicationDTO;
import board.bean.BoardDTO;
import board.bean.NoticeDTO;

public interface BoardService {
	public int boardWrite(BoardDTO boardDTO);
	
	public List<BoardDTO> boardList(int startNum, int endNum);

	public BoardDTO boardView(int seq);
	
	public int updateHit(int seq);

	public int getTotalA();

	public int boardDelete(int seq);

	public List<NoticeDTO> noticeList();
	
	public int boardUpdate(BoardDTO boardDTO);

	public int noticeInsert(NoticeDTO noticeDTO);

	public int noticeDelete(int seq);

	public int noticeUpdate(NoticeDTO noticeDTO);

	public List<ApplicationDTO> applicationList(int startNum, int endNum);
	
	public int getTotalB();

	public int getTotalC();
	
}

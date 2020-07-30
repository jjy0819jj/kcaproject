package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.bean.ApplicationDTO;
import board.bean.BoardDTO;
import board.bean.NoticeDTO;

@Repository
public class BoardDAO {
	@Autowired
    private SqlSessionTemplate sqlSession;
	
	public int boardWrite(BoardDTO boardDTO) {
        return sqlSession.insert("board.boardWrite", boardDTO);
	}

	public List<BoardDTO> boardList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("board.boardList", map);
	}

	public BoardDTO boardView(int seq) {
		return sqlSession.selectOne("board.boardView", seq);
	}

	public int updateHit(int seq) {
		return sqlSession.update("board.updateHit", seq);
	}

	public int getTotalA() {
		return sqlSession.selectOne("board.getTotalA");
	}

	public int boardDelete(int seq) {
        return sqlSession.delete("board.boardDelete", seq);
	}

	public List<NoticeDTO> noticeList() {
		return sqlSession.selectList("board.noticeList");
	}

	public int boardUpdate(BoardDTO boardDTO) {
		return sqlSession.update("board.boardUpdate", boardDTO);
	}

	public int noticeWrite(NoticeDTO noticeDTO) {
		return sqlSession.insert("board.noticeWrite", noticeDTO);
	}

	public int noticeDelete(int seq) {
		return sqlSession.delete("board.noticeDelete", seq);
	}

	public int noticeUpdate(NoticeDTO noticeDTO) {
		return sqlSession.update("board.noticeUpdate", noticeDTO);
	}
	
	public List<ApplicationDTO> applicationList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("board.applicationList", map);
	}
	
	public int getTotalB() {
		return sqlSession.selectOne("board.getTotalB");
	}

	public int getTotalC() {
		return sqlSession.selectOne("board.getTotalC");
	}
	
}

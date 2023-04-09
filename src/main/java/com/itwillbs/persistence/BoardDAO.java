package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	
	public void writeBoard(BoardVO vo);
	
	public List<BoardVO> getBoardList();
}

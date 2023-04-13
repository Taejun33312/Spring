package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	public void writeBoard(BoardVO vo);
	
	public List<BoardVO> getBoardList();
	
	public BoardVO getBoardContent(BoardVO vo);
	
}

package com.itwillbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO bdao;

	@Override
	public void writeBoard(BoardVO vo) {
		bdao.writeBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = bdao.getBoardList();
		return boardList;
	}

	@Override
	public BoardVO getBoardContent(BoardVO vo) {

		vo = bdao.getBoardContent(vo);
		
		return vo;
	}

}

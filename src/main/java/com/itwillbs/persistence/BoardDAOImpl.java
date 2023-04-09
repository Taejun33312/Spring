package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Override
	public void writeBoard(BoardVO vo) {

		int result = sqlSession.insert(NAMESPACE + ".writeBoard", vo);

		if (result > 0) {
			System.out.println("DAO : 게시판 글 쓰기 성공");
		}
	}

	@Override
	public List<BoardVO> getBoardList() {
		
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+".getBoardList");
		
		return boardList;
	}
	
}

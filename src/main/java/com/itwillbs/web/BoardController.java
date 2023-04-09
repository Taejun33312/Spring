package com.itwillbs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;

	// http://localhost:8080/board/boardList
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		
		List<BoardVO> boardList = service.getBoardList();
		
		model.addAllAttributes(boardList);
		
		return "/board/boardList";
	}
	
	
	// http://localhost:8080/board/write
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String writeGET() {
		
		return "/board/writeForm";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String writePost(BoardVO vo) {
		
		service.writeBoard(vo);
		
		return "redirect:/board/boardList";
	}
	
}

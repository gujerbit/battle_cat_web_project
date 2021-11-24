package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.BoardServiceImpl;
import com.gujerbit.battle_cat_web.util.RSA;
import com.gujerbit.battle_cat_web.vo.BoardVO;

@CrossOrigin("*")
@Controller
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	private RSA rsa;
	
	@PostMapping("/board_writing")
	public @ResponseBody int writing(@RequestBody BoardVO vo) {
//		return boardService.writing(vo);
		System.out.println(vo.getContent());
		
		return 0;
	}
	
	@GetMapping("/get_board_list")
	public @ResponseBody List<BoardVO> getAllBoardList() {
		return boardService.getAllBoardData();
	}
	
	@GetMapping("/get_board_data/{idx}")
	public @ResponseBody BoardVO getBoardData(@PathVariable int idx) {
		return boardService.getBoardDate(idx);
	}
	
}

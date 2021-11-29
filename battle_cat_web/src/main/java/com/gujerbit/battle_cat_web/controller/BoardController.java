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
import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.util.Hashing;
import com.gujerbit.battle_cat_web.util.RSA;
import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@CrossOrigin("*")
@Controller
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private Hashing hashing;
	
	@Autowired
	private RSA rsa;
	
	@PostMapping("/board_writing")
	public @ResponseBody int writing(@RequestBody BoardVO vo) {
		return boardService.writing(vo);
	}
	
	@GetMapping("/get_board_list")
	public @ResponseBody List<BoardVO> getAllBoardList() {
		return boardService.getAllBoardData();
	}
	
	@GetMapping("/get_board_data/{idx}")
	public @ResponseBody BoardVO getBoardData(@PathVariable int idx) {
		return boardService.getBoardData(idx);
	}
	
	@GetMapping("/get_board_count_list")
	public @ResponseBody List<BoardCountVO> getAllBoardCountData() {
		return boardService.getAllBoardCountData();
	}
	
	@PostMapping("/count_update")
	public @ResponseBody int countUpdate(@RequestBody BoardCountVO vo) {
		if(boardService.duplicateCheck(vo) <= 0) {
			return boardService.countUpdate(vo);
		} else {
			return 0;
		}
	}
	
	@GetMapping("/get_board_count/{idx}")
	public @ResponseBody List<BoardCountVO> getBoardCountData(@PathVariable int idx) {
		return boardService.getCountData(idx);
	}
	
	@PostMapping("/delete_board")
	public @ResponseBody int deleteBoard(@RequestBody BoardVO vo) {
		return boardService.deleteBoard(vo);
	}
	
	@PostMapping("/delete_board_check")
	public @ResponseBody boolean deleteBoardCheck(@RequestBody UserVO vo) {
		String password = vo.getPassword();
		String digestPassword = hashing.hashing(password.getBytes());
		String salt = userService.selectSalt(vo.getEmail());
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		vo.setPassword(saltingPassword);
		
		return boardService.deleteBoardCheck(vo) != null ? true : false;
	}
	
}

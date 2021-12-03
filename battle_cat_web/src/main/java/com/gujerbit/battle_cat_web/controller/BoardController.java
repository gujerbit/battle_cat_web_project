package com.gujerbit.battle_cat_web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gujerbit.battle_cat_web.vo.CommentVO;

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
	
	@PostMapping("/write_board")
	public @ResponseBody int writing(@RequestBody BoardVO vo) {
		if(vo.getContent().length() > 10000 || vo.getContent().length() <= 0 || vo.getTitle().length() > 50 || vo.getTitle().length() <= 0) throw new RuntimeException();
		
		return boardService.writing(vo);
	}
	
	@GetMapping("/get_board_list/{size}")
	public @ResponseBody List<BoardVO> getAllBoardList(@PathVariable int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", size);
		map.put("end", size + 99);
		
		return boardService.getAllBoardData(map);
	}
	
	@GetMapping("/get_board_count_list/{size}")
	public @ResponseBody List<BoardCountVO> getAllBoardCountData(@PathVariable int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", size);
		map.put("end", size + 99);
		
		return boardService.getAllBoardCountData(map);
	}
	
	@GetMapping("/get_board_list_size")
	public @ResponseBody int getAllBoardListSize() {
		return boardService.selectAllBoardDataSize();
	}
	
	@GetMapping("/get_board_count_list_size")
	public @ResponseBody int getAllBoardCountDataSize() {
		return boardService.selectAllBoardCountDataSize();
	}
	
	@GetMapping("/get_board_data/{idx}")
	public @ResponseBody BoardVO getBoardData(@PathVariable int idx) {
		return boardService.getBoardData(idx);
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
	
	@PostMapping("/update_board")
	public @ResponseBody int updateBoard(@RequestBody BoardVO vo) {
		if(vo.getContent().length() > 10000 || vo.getContent().length() <= 0 || vo.getTitle().length() > 50 || vo.getTitle().length() <= 0) throw new RuntimeException();
		
		return boardService.updateBoard(vo);
	}
	
	@PostMapping("/writing_comment")
	public @ResponseBody int writingComment(@RequestBody CommentVO vo) {
		if(vo.getComment().length() > 300 || vo.getComment().length() <= 0) throw new RuntimeException();
		
		return boardService.writingComment(vo);
	}
	
	@GetMapping("/get_comment_data/{idx}")
	public @ResponseBody List<CommentVO> getCommentData(@PathVariable int idx) {
		return boardService.getCommentData(idx);
	}
	
	@PostMapping("/update_comment")
	public @ResponseBody int updateComment(@RequestBody CommentVO vo) {
		if(vo.getComment().length() > 300 || vo.getComment().length() <= 0) throw new RuntimeException();
		
		return boardService.updateComment(vo);
	}
	
	@PostMapping("/delete_comment")
	public @ResponseBody int deleteComment(@RequestBody CommentVO vo) {
		return boardService.deleteComment(vo);
	}
	
//	@GetMapping("/get_user_board_list/{email}")
//	public @ResponseBody List<BoardVO> getUserBoardList(@PathVariable String name) {
//		
//	}
	
}

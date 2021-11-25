package com.gujerbit.battle_cat_web.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		return boardService.writing(vo);
	}
	
	@GetMapping("/get_board_list")
	public @ResponseBody List<BoardVO> getAllBoardList() {
		return boardService.getAllBoardData();
	}
	
	@GetMapping("/get_board_data/{idx}")
	public @ResponseBody BoardVO getBoardData(@PathVariable int idx) {
		return boardService.getBoardDate(idx);
	}
	
	@PostMapping("/board_image_upload")
	public @ResponseBody String BoardImageUpload(@RequestParam("image") List<MultipartFile> files) throws Exception {
		File save = new File("");
		
		for(MultipartFile file : files) {
//			if(!file.isEmpty()) {
//				if(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/gif")) {
//					String fileName = Long.toString(System.nanoTime());
//					
//					if(file.getContentType().equals("image/png")) fileName += ".png";
//					else if(file.getContentType().equals("image/jpeg")) fileName += ".jpg";
//					else if(file.getContentType().equals("image/gif")) fileName += ".gif";
//				}
//			}
			save = new File(System.getProperty("user.dir") + "/src/main/resources/" + file.getOriginalFilename());
			file.transferTo(save);
		}
		
		System.out.println(save.getAbsolutePath());
		System.out.println(save.getPath());
		System.out.println(save.getName());
		
		return save.getAbsolutePath();
	}
	
}

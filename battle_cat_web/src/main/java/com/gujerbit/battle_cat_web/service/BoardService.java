package com.gujerbit.battle_cat_web.service;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.BoardVO;

public interface BoardService {

	int writing(BoardVO vo);
	
	List<BoardVO> getAllBoardData();
	
	BoardVO getBoardDate(int idx);
	
	int viewCountUpdate(int idx);
	
}

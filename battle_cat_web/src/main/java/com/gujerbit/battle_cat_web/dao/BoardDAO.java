package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.BoardVO;

@Mapper
public interface BoardDAO {

	public int writing(BoardVO vo);
	
	public List<BoardVO> getAllBoardData();
	
	public BoardVO getBoardDate(int idx);
	
}

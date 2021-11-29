package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Mapper
public interface BoardDAO {

	public int writing(BoardVO vo);
	
	public List<BoardVO> getAllBoardData();
	
	public BoardVO getBoardData(int idx);
	
	public List<BoardCountVO> getAllBoardCountData();
	
	public int duplicateCheck(BoardCountVO vo);
	
	public int countUpdate(BoardCountVO vo);
	
	public List<BoardCountVO> getCountData(int idx);
	
	public int deleteBoard(BoardVO vo);
	
	public int updateBoard(BoardVO vo);
	
}

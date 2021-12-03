package com.gujerbit.battle_cat_web.service;

import java.util.List;
import java.util.Map;

import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
import com.gujerbit.battle_cat_web.vo.CommentVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

public interface BoardService {

	int writing(BoardVO vo);
	
	List<BoardVO> getAllBoardData(Map<String, Integer> map);
	
	List<BoardCountVO> getAllBoardCountData(Map<String, Integer> map);
	
	int selectAllBoardDataSize();
	
	int selectAllBoardCountDataSize();
	
	BoardVO getBoardData(int idx);
	
	int duplicateCheck(BoardCountVO vo);
	
	int countUpdate(BoardCountVO vo);
	
	List<BoardCountVO> getCountData(int idx);
	
	int deleteBoard(BoardVO vo);
	
	int updateBoard(BoardVO vo);
	
	int writingComment(CommentVO vo);
	
	List<CommentVO> getCommentData(int idx);
	
	int updateComment(CommentVO vo);
	
	int deleteComment(CommentVO vo);
	
}

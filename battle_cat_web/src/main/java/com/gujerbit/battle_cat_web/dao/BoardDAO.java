package com.gujerbit.battle_cat_web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
import com.gujerbit.battle_cat_web.vo.CommentVO;

@Mapper
public interface BoardDAO {

	public int writing(BoardVO vo);
	
	public List<BoardVO> getAllBoardData(Map<String, Integer> map);
	
	public List<BoardCountVO> getAllBoardCountData(Map<String, Integer> map);
	
	public int selectAllBoardDataSize();
	
	public int selectAllBoardCountDataSize();
	
	public BoardVO getBoardData(int idx);
	
	public int duplicateCheck(BoardCountVO vo);
	
	public int countUpdate(BoardCountVO vo);
	
	public List<BoardCountVO> getCountData(int idx);
	
	public int deleteBoard(BoardVO vo);
	
	public int updateBoard(BoardVO vo);
	
	public int writingComment(CommentVO vo);
	
	public int selectCommentListSize();
	
	public List<CommentVO> selectCommentList(Map<String, Integer> map);
	
	public List<CommentVO> getCommentData(int idx);
	
	public int updateComment(CommentVO vo);
	
	public int deleteComment(CommentVO vo);
	
	public List<BoardVO> selectUserBoardList(Map<String, Object> map);
	
	public List<CommentVO> selectUserCommentList(Map<String, Object> map);
	
	public int selectUserBoardListSize(BoardVO vo);
	
	public int selectUserCommentListSize(CommentVO vo);
	
}

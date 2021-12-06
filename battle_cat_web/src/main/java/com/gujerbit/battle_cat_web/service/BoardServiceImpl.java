package com.gujerbit.battle_cat_web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.BoardDAO;
import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
import com.gujerbit.battle_cat_web.vo.CommentVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;
	
	@Override
	public int writing(BoardVO vo) {
		return dao.writing(vo);
	}
	
	@Override
	public List<BoardVO> getAllBoardData(Map<String, Integer> map) {
		return dao.getAllBoardData(map);
	}
	
	@Override
	public List<BoardCountVO> getAllBoardCountData(Map<String, Integer> map) {
		return dao.getAllBoardCountData(map);
	}
	
	@Override
	public int selectAllBoardDataSize() {
		return dao.selectAllBoardDataSize();
	}
	
	@Override
	public int selectAllBoardCountDataSize() {
		return dao.selectAllBoardCountDataSize();
	}
		
	@Override
	public BoardVO getBoardData(int idx) {
		return dao.getBoardData(idx);
	}
	
	@Override
	public int duplicateCheck(BoardCountVO vo) {
		return dao.duplicateCheck(vo);
	}
	
	@Override
	public int countUpdate(BoardCountVO vo) {
		return dao.countUpdate(vo);
	}
	
	@Override
	public List<BoardCountVO> getCountData(int idx) {
		return dao.getCountData(idx);
	}
	
	@Override
	public int deleteBoard(BoardVO vo) {
		return dao.deleteBoard(vo);
	}
	
	@Override
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}
	
	@Override
	public int writingComment(CommentVO vo) {
		return dao.writingComment(vo);
	}
	
	@Override
	public int selectCommentListSize() {
		return dao.selectCommentListSize();
	}
	
	@Override
	public List<CommentVO> selectCommentList(Map<String, Integer> map) {
		return dao.selectCommentList(map);
	}
	
	@Override
	public List<CommentVO> getCommentData(int idx) {
		return dao.getCommentData(idx);
	}
	
	@Override
	public int updateComment(CommentVO vo) {
		return dao.updateComment(vo);
	}
	
	@Override
	public int deleteComment(CommentVO vo) {
		return dao.deleteComment(vo);
	}
	
}

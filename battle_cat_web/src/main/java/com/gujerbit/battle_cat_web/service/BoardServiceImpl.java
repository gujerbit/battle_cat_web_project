package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.BoardDAO;
import com.gujerbit.battle_cat_web.vo.BoardCountVO;
import com.gujerbit.battle_cat_web.vo.BoardVO;
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
	public List<BoardVO> getAllBoardData() {
		return dao.getAllBoardData();
	}
	
	@Override
	public BoardVO getBoardData(int idx) {
		return dao.getBoardData(idx);
	}
	
	@Override
	public List<BoardCountVO> getAllBoardCountData() {
		return dao.getAllBoardCountData();
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
	
}

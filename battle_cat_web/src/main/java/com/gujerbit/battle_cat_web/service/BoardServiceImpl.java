package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.BoardDAO;
import com.gujerbit.battle_cat_web.vo.BoardVO;

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
	public BoardVO getBoardDate(int idx) {
		return dao.getBoardDate(idx);
	}
	
}

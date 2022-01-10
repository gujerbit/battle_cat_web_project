package com.gujerbit.battle_cat_web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.StageDataDAO;
import com.gujerbit.battle_cat_web.vo.StageDataVO;

@Service
public class StageDataServiceImpl implements StageDataService {
	
	@Autowired
	private StageDataDAO dao;
	
	@Override
	public List<StageDataVO> selectAllStageData(int start) {
		return dao.selectAllStageData(start);
	}
	
	@Override
	public int selectAllStageDataSize() {
		return dao.selectAllStageDataSize();
	}
	
//	@Override
//	public List<StageDataVO> selectAllWorldStageData() {
//		return dao.selectAllWorldStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectAllFutureStageData() {
//		return dao.selectAllFutureStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectAllSpaceStageData() {
//		return dao.selectAllSpaceStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectAllLegendStageData() {
//		return dao.selectAllLegendStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectAllOriginLegendStageData() {
//		return dao.selectAllOriginLegendStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectEventStageData() {
//		return dao.selectEventStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectGuerrillaStageData() {
//		return dao.selectGuerrillaStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectWeekStageData(String week) {
//		return dao.selectWeekStageData(week);
//	}
//	
//	@Override
//	public List<StageDataVO> selectDropStageData() {
//		return dao.selectDropStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectAdventStageData() {
//		return dao.selectAdventStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectCrazyStageData() {
//		return dao.selectCrazyStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectCycloneStageData() {
//		return dao.selectCycloneStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectArousalStageData() {
//		return dao.selectArousalStageData();
//	}
//	
//	@Override
//	public List<StageDataVO> selectExcavationStageData(String grade) {
//		return dao.selectExcavationStageData(grade);
//	}
//	
//	@Override
//	public List<StageDataVO> selectIdStageData(Map<String, String> map) {
//		return dao.selectIdStageData(map);
//	}
//	
//	@Override
//	public List<StageDataVO> selectNameStageData(Map<String, String> map) {
//		return dao.selectNameStageData(map);
//	}

}

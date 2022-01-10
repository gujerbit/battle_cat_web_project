package com.gujerbit.battle_cat_web.service;

import java.util.List;
import java.util.Map;

import com.gujerbit.battle_cat_web.vo.StageDataVO;

public interface StageDataService {
	
	List<StageDataVO> selectAllStageData(int start);
	
	int selectAllStageDataSize();

//	List<StageDataVO> selectAllWorldStageData();
//	
//	List<StageDataVO> selectAllFutureStageData();
//	
//	List<StageDataVO> selectAllSpaceStageData();
//	
//	List<StageDataVO> selectAllLegendStageData();
//
//	List<StageDataVO> selectAllOriginLegendStageData();
//	
//	List<StageDataVO> selectEventStageData();
//	
//	List<StageDataVO> selectGuerrillaStageData();
//	
//	List<StageDataVO> selectWeekStageData(String week);
//	
//	List<StageDataVO> selectDropStageData();
//	
//	List<StageDataVO> selectAdventStageData();
//	
//	List<StageDataVO> selectCrazyStageData();
//	
//	List<StageDataVO> selectCycloneStageData();
//	
//	List<StageDataVO> selectArousalStageData();
//	
//	List<StageDataVO> selectExcavationStageData(String grade);
//	
//	List<StageDataVO> selectIdStageData(Map<String, String> map);
//	
//	List<StageDataVO> selectNameStageData(Map<String, String> map);
	
}

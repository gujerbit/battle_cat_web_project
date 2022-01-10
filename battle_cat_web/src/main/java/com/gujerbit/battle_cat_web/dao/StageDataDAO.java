package com.gujerbit.battle_cat_web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.StageDataVO;

@Mapper
public interface StageDataDAO {
	
	public List<StageDataVO> selectAllStageData(int start);
	
	public int selectAllStageDataSize();

//	public List<StageDataVO> selectAllWorldStageData();
//	
//	public List<StageDataVO> selectAllFutureStageData();
//	
//	public List<StageDataVO> selectAllSpaceStageData();
//	
//	public List<StageDataVO> selectAllLegendStageData();
//	
//	public List<StageDataVO> selectAllOriginLegendStageData();
//	
//	public List<StageDataVO> selectEventStageData();
//	
//	public List<StageDataVO> selectGuerrillaStageData();
//	
//	public List<StageDataVO> selectWeekStageData(String week);
//	
//	public List<StageDataVO> selectDropStageData();
//	
//	public List<StageDataVO> selectAdventStageData();
//	
//	public List<StageDataVO> selectCrazyStageData();
//	
//	public List<StageDataVO> selectCycloneStageData();
//	
//	public List<StageDataVO> selectArousalStageData();
//	
//	public List<StageDataVO> selectExcavationStageData(String grade);
//	
//	public List<StageDataVO> selectIdStageData(Map<String, String> map);
//	
//	public List<StageDataVO> selectNameStageData(Map<String, String> map);
	
}

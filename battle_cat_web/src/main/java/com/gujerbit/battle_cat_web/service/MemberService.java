package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.MemberDAO;
import com.gujerbit.battle_cat_web.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO instance;
	
	public List<MemberVO> selectAllMembers() {
		return instance.selectAllMembers();
	}
	
}

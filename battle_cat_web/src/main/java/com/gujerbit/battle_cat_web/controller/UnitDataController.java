package com.gujerbit.battle_cat_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gujerbit.battle_cat_web.service.UnitDataService;

@Controller
public class UnitDataController {

	@Autowired
	private UnitDataService service;
	
}

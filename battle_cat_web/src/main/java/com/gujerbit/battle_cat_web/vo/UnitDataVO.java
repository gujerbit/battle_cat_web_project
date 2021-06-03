package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnitDataVO {
	// ���� ��ȣ
	@JsonProperty
	private String id;
	// ĳ���� �̸�
	@JsonProperty
	private String name;
	// ĳ���� ����
	@JsonProperty
	private String explanation;
	// ���
	@JsonProperty
	private String rarity;
	// ĳ���� �̹��� ���
	@JsonProperty
	private String image_code;
	// ü��
	@JsonProperty
	private int hp;
	// ��Ʈ��
	@JsonProperty
	private int hit_back;
	// ���ݷ�
	@JsonProperty
	private int attack_power;
	// �̵� �ӵ�
	@JsonProperty
	private int move_speed;
	// ���� �ӵ�
	@JsonProperty
	private int produce_speed;
	// ���� �ӵ� (����)
	@JsonProperty
	private int attack_speed;
	// ���� �簣�� �ð� (�ĵ�)
	@JsonProperty
	private int attack_frequency;
	// �ν� ��Ÿ�
	@JsonProperty
	private int attack_range;
	// ���
	@JsonProperty
	private int cost;
	
	@JsonProperty
	private int long_multi_range_start;
	
	@JsonProperty
	private int long_multi_range_end;
	
}

package com.gujerbit.battle_cat_web.vo;

public class TempVO {
	private String msg;
	private int age;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "TempVO [msg=" + msg + ", age=" + age + "]";
	}
}

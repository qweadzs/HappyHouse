package com.ssafy.happyhouse.model;

public class NewsDto {
	private String newstitle;
	private String newscontent;

	public NewsDto(String newstitle, String newscontent) {
		super();
		this.newstitle = newstitle;
		this.newscontent = newscontent;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public String getNewscontent() {
		return newscontent;
	}
	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}
	
}

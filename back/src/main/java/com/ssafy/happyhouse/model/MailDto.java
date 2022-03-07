package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Mail : 메일정보 ", description = "메일의 상세 정보를 나타낸다.")
public class MailDto {

	@ApiModelProperty(value = "메일 제목 ")
	private String mailname;
	@ApiModelProperty(value = "메일 내용")
	private String mailcontent;
	public String getMailname() {
		return mailname;
	}
	public void setMailname(String mailname) {
		this.mailname = mailname;
	}
	public String getMailcontent() {
		return mailcontent;
	}
	public void setMailcontent(String mailcontent) {
		this.mailcontent = mailcontent;
	}

}

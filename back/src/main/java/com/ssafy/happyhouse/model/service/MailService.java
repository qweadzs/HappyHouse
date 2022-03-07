package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MailDto;

public interface MailService {

	public boolean mailWrite(MailDto mailDto) throws Exception;
	
}

package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.MailDto;

@Mapper
public interface MailMapper {
	public boolean mailWrite(MailDto mailDto) throws SQLException;
	public List<String> mailFind() throws SQLException;
	
}
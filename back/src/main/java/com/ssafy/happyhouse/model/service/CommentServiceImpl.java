package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CommentDto> list(int articleNo) {
		return sqlSession.getMapper(CommentMapper.class).list(articleNo);
	}
	@Override
	public boolean create(CommentDto commentDto) {
		return sqlSession.getMapper(CommentMapper.class).create(commentDto) == 1;
	}
	@Override
	public boolean modify(CommentDto commentDto) {
		return sqlSession.getMapper(CommentMapper.class).modify(commentDto) == 1;
	}
	@Override
	public boolean delete(int commentNo) {
		System.out.println("delete 실행");
		return sqlSession.getMapper(CommentMapper.class).delete(commentNo) == 1;
	}
}

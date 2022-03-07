package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.WishListDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}
	// 회원가입
	@Override
	public boolean signupMember(MemberDto memberDto) throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.getMapper(MemberMapper.class).signupMember(memberDto)==1;
	}

	@Override
	@Transactional
	public boolean deleteUser(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).deleteUser(userid) == 1;
	}

	@Override
	public MemberDto getUser(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).getUser(userid);
	}

	@Override
	@Transactional
	public boolean modifyUser(MemberDto memberDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).modifyUser(memberDto) == 1;
	}

	@Override
	public boolean registSub(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).registSub(userid) == 1;
	}

	@Override
	public List<WishListDto> listWish(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).listWish(userid);
	}

	@Override
	public boolean deleteWish(int no) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).deleteWish(no) == 1;
	}

}

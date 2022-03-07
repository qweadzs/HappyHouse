package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.WishListDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public int signupMember(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int deleteUser(String userid) throws SQLException;
	public MemberDto getUser(String userid) throws SQLException;
	public int modifyUser(MemberDto memberDto) throws SQLException;
	public int registSub(String userid) throws SQLException;
	public List<WishListDto> listWish(String userid)throws SQLException;
	public int deleteWish(int no) throws SQLException;
}

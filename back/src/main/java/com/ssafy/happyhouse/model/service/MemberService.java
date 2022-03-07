package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.WishListDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean signupMember(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean deleteUser(String userid) throws Exception;
	public MemberDto getUser(String userid) throws Exception;
	public boolean modifyUser(MemberDto memberDto) throws Exception;
	public boolean registSub(String userid) throws Exception;
	public List<WishListDto> listWish(String userid)throws Exception;
	public boolean deleteWish(int no)throws Exception;
}

package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.WishListDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원가입", notes = "회원가입한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody @ApiParam(value = "회원 정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("singup함수 - 호출");
//		System.out.println(memberDto.getEmail());
		if (memberService.signupMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value = "회원 탈퇴", notes = "회원아이디에 해당하는 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") @ApiParam(value = "삭제할 회원번호.", required = true) String userid) throws Exception {
		logger.info("deleteUser - 호출");
		if (memberService.deleteUser(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value = "회원정보", notes = "회원정보에 해당하는 회원 정보를 반환한다.", response = MemberDto.class)
	@GetMapping("/update/{userid}")
	public ResponseEntity<MemberDto> getUser(@PathVariable("userid") @ApiParam(value = "얻어올 회원의 아이디.", required = true) String userid) throws Exception {
		logger.info("getUser - 호출 : " + userid);
		System.out.println("회원호출");
		return new ResponseEntity<MemberDto>(memberService.getUser(userid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 수정", notes = "새로운 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/update")
	public ResponseEntity<String> modifyUser(@RequestBody @ApiParam(value = "수정할 회원정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("modifyUser - 호출");
		
		if (memberService.modifyUser(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "구독회원등록 ", notes = "구독회원등록. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/subscribe/{userid}")
	public ResponseEntity<String> registSub(@PathVariable("userid") @ApiParam(value = "회원등록 .", required = true) String userid) throws Exception {
		logger.info("registSub함수 - 호출");
		System.out.println(userid);
		if (memberService.registSub(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "wishlist 글목록", notes = "모든 wishlist의 정보를 반환한다.", response = List.class)
	@GetMapping("/wishlist/{userid}")
	public ResponseEntity<List<WishListDto>> listWish(@PathVariable("userid") @ApiParam(value = "위시리스트 얻기위한 부가정보.", required = true) String userid) throws Exception {
		logger.info("listWish - 호출");
		return new ResponseEntity<List<WishListDto>>(memberService.listWish(userid), HttpStatus.OK);
	}
	@ApiOperation(value = "wishrow 삭제", notes = "선택한 wishrow를 삭제한다.", response = List.class)
	@DeleteMapping("/wishlist/{no}")
	public ResponseEntity<String> deleteWish(@PathVariable("no") @ApiParam(value = "선택한 위시리스트 row를 삭제한다.", required = true) int no) throws Exception {
		logger.info("listWish - 호출");
		if (memberService.deleteWish(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}

package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.ssafy.happyhouse.model.MailDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MailService;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/news")
@Api("사용자 컨트롤러  API V1")
public class NewsController {

	public static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@ApiOperation(value= "뉴스리스", notes = "뉴스리스트 . 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@GetMapping
	public ResponseEntity<LinkedList<NewsDto>>newsList() throws Exception {
		
		LinkedList<NewsDto> newslist= new LinkedList<NewsDto>(); 
		
		String url = "https://land.naver.com/news/field.naver";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 주요 뉴스로 나오는 태그를 찾아서 가져오도록 한다.
		Elements element = doc.select("div.section_headline");
		
		// 1. 헤더 부분의 제목을 가져온다.
//		String title = element.select("ul").text().substring(0, 4);

//		System.out.println("============================================================");
//		System.out.println(title);
//		System.out.println("============================================================");
		int idx = 0;
		for(Element el : element.select("dt")) {	// 하위 뉴스 기사들을 for문 돌면서 출력
//			System.out.println(el.text());
			if(idx ==5) break;
			String newstitle =el.text();
			String newscontent = el.select("[href]").attr("abs:href"); 
			newslist.add(new NewsDto(newstitle,newscontent));
			idx ++;
		}
		return new ResponseEntity<LinkedList<NewsDto>>(newslist, HttpStatus.OK);
	
		
	}
}

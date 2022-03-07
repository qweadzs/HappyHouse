package com.ssafy.happyhouse.model.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MailDto;
import com.ssafy.happyhouse.model.mapper.MailMapper;

@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public boolean mailWrite(MailDto mailDto) throws Exception {
		// TODO Auto-generated method stub
		List<String> mailList = sqlSession.getMapper(MailMapper.class).mailFind();
		Boolean flag = false;
		for(String email : mailList) {
			Properties p = System.getProperties();
	        p.put("mail.smtp.starttls.enable", "true");     // gmail은 true 고정
	        p.put("mail.smtp.host", "smtp.naver.com");      // smtp 서버 주소
	        p.put("mail.smtp.auth","true");                 // gmail은 true 고정
	        p.put("mail.smtp.port", "587");                 // 네이버 포트
	        p.put("mail.smtp.port", "587");                 // 네이버 포트
	           
	        Authenticator auth = new MyAuthentication();
	        //session 생성 및  MimeMessage생성
	        Session session = Session.getDefaultInstance(p, auth);
	        MimeMessage msg = new MimeMessage(session);
	         
	        try{
	            //편지보낸시간
	            msg.setSentDate(new Date());
	            InternetAddress from = new InternetAddress() ;
	            from = new InternetAddress("qweadzs@naver.com"); //발신자 아이디
	            // 이메일 발신자
	            msg.setFrom(from);
	            // 이메일 수신자
	            // qweadzs@naver.com
	            InternetAddress to = new InternetAddress(email);
	            msg.setRecipient(Message.RecipientType.TO, to);
	            // 이메일 제목
	            msg.setSubject(mailDto.getMailname(), "UTF-8");
	            // 이메일 내용
	            msg.setText(mailDto.getMailcontent(), "UTF-8");
	            // 이메일 헤더
	            msg.setHeader("content-Type", "text/html");
	            //메일보내기
	            javax.mail.Transport.send(msg, msg.getAllRecipients());
	            System.out.println("성공 ");
	        }catch (AddressException addr_e) {
	            addr_e.printStackTrace();
	        }catch (MessagingException msg_e) {
	            msg_e.printStackTrace();
	        }catch (Exception msg_e) {
	            msg_e.printStackTrace();
	        }
	        flag = true;
		}

		return flag;
	}
	
	public class MyAuthentication extends Authenticator {
	      
	    PasswordAuthentication pa;
	    public MyAuthentication(){
	         
	        String id = "qweadzs@naver.com";  //네이버 이메일 아이디
	        String pw = "naverehfkdl77!";        //네이버 비밀번호
	 
	        // ID와 비밀번호를 입력한다.
	        pa = new PasswordAuthentication(id, pw);
	    }
	 
	    // 시스템에서 사용하는 인증정보
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }
	} 
}

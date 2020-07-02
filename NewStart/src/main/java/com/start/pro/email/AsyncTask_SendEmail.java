package com.start.pro.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.email.IService_Email;
import com.start.pro.util.Util_JSON;



@Service
public class AsyncTask_SendEmail {


	private final String setFrom = "ckadl0118@gmail.com";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IService_Email service;

	@Autowired
	private Util_JSON jsonUtil;

	// 단일 메세지 전송
	@Async("myex")
	public void sendOneMail(DTO_Email dto, HttpServletResponse resp) {

		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(dto.getUser_email());
			messageHelper.setSubject(dto.getEmail_title());
			messageHelper.setText(dto.getEmail_content(),true);

			mailSender.send(message);
			
		} catch (Exception e) {

		}

	}

	public boolean sendReplyMail(String email, String title, String content){

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(email);
			messageHelper.setSubject(title);
			messageHelper.setText(content,true);
			
			mailSender.send(message);
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	@Async("myex")
	public void LJMail(String code, String email) {

		DTO_Email dto = service.SelDetailAuto(code);
		String key = randomKey();

		String content = dto.getEmail_content();
		content = content.replace("#{email}", email);
		content =content.replace("#{key}", key);
		dto.setEmail_content(content);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", email);
		map.put("lj_key",key);
		map.put("lj_code",code);
		service.sendLJ(map);


		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(email);
			messageHelper.setSubject(dto.getEmail_title());
			messageHelper.setText(dto.getEmail_content(),true);

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	//대량 메일 발송
	@Async("myex")
	public void sendManyMail(DTO_Email mailList) {

		// 기존 
		List<String> mails = jsonUtil.jsonToList(mailList.getUser_email(),"user_email");

		MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
		int i = 0;
		for (String mail : mails) {
			
			DTO_User udto = service.getinfo(mail);
			String content = mailList.getEmail_content().replace("${name}", udto.getUser_name()).replace("${nickname}", udto.getUser_nickname());
			
			preparators[i++] = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					helper.setFrom(setFrom);
					helper.setTo(mail);
					helper.setSubject(mailList.getEmail_title());
					helper.setText(content, true);
				}
			};
		}

		mailSender.send(preparators);

		try {
			Thread.sleep(3000);
			chkFailMail(mailList, mails);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//재전송
	@Async("myex")
	public void resend(DTO_Email mailList) {

		List<String> mails = jsonUtil.jsonToList(mailList.getUser_email(),"user_email");

		MimeMessagePreparator[] preparators = new MimeMessagePreparator[mails.size()];
		int i = 0;
		for (String mail : mails) {

			preparators[i++] = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					helper.setFrom(setFrom);
					helper.setTo(mail);
					helper.setSubject(mailList.getEmail_title());
					helper.setText(mailList.getEmail_content(), true);
				}
			};
		}

		mailSender.send(preparators);

		try {
			Thread.sleep(3000);
			chkFailMail(mailList, mails);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//반송 이메일 확인하기
	private void chkFailMail(DTO_Email mailList, List<String> mails) throws Exception {


		String host = "imap.gmail.com"; //imap 호스트 주소. ex) imap.gmail.com
		String userEmail = "ckadl0118@gmail.com"; //유저 이메일 주소
		String password = "snfl29336313!"; //유저 암호

		URLName url = new URLName("imaps", host, 993, "INBOX", userEmail, password);

		Session session= null;
		Store store = null;
		Folder folder = null;	

		if (session == null) {
			Properties props = System.getProperties();
			session = Session.getInstance(props, null);
		}
		store = session.getStore(url);
		store.connect();
		folder = store.getFolder("inbox"); //inbox는 받은 메일함을 의미

		if(folder==null || !folder.exists()) {
			return;
		}

		folder.open(Folder.READ_WRITE); //읽고 쓰기 전용

		Message[] msgArray = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		if(msgArray.length == 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("email_seq", mailList.getEmail_seq());
			map.put("successchk", "Y");
			service.MailSuccess(map);
			return;
		}else {
			List<String> failMails = failmailchk(folder, msgArray);

			if(failMails.size() == 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("email_seq", mailList.getEmail_seq());
				map.put("successchk", "Y");
				service.MailSuccess(map);
				return;
			}else if(mails.size() != failMails.size()) {

				for (String failMail : failMails) {
					mails.remove(failMail);
				}

				String sucmail = jsonUtil.listToJson(mails,"user_email");

				// 실패 제외한 나머지 이메일들
				Map<String, String> map = new HashMap<String, String>();
				map.put("email_seq", mailList.getEmail_seq());
				map.put("user_email", sucmail);
				map.put("successchk", "Y");
				service.MailSuccess(map);

				//실패 insert
				mailList.setUser_email(jsonUtil.listToJson(failMails,"user_email"));
				mailList.setSuccesschk("F");

				service.SendEmail(mailList);
			}else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("successchk", "F");
				map.put("email_seq", mailList.getEmail_seq());
				service.MailSuccess(map);
			}

		}

		folder.close(true);
		store.close();
		folder = null;
		store = null;
		session = null;
	}

	private List<String> failmailchk(Folder folder, Message[] msgArray) throws Exception {

		//실패한 메일 모음
		List<String> failMails = new ArrayList<String>();
		for (int i = 0; i < msgArray.length; i++) {

			Message msg = msgArray[i];
			if(msg.getSubject().equalsIgnoreCase("Delivery Status Notification (Failure)")) {
				String result = saveParts(msg.getContent());
				System.out.println("이거 리턴돼?"+result);
				if(result!=null && failMails.indexOf(result) == -1) {
					failMails.add(result);
				}
				folder.setFlags(new Message[] {msg}, new Flags(Flags.Flag.SEEN), true);
			}
		}
		return failMails;
	}

	//메일 주소 도출하기
	private String saveParts(Object content) throws Exception { 

		String failEmail = null;

		if (content instanceof Multipart) {

			Multipart multi = ((Multipart) content);

			for (int j = 0; j < multi.getCount(); j++) {

				MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);

				if (part.getContent() instanceof Multipart) {
					String result = saveParts(part.getContent());
					if(result != null) {
						failEmail = result;
					}
				} else if(part.isMimeType("text/plain")){
					String str = (String) part.getContent();
					str = (str.substring(str.indexOf("to")+2, str.indexOf("because"))).trim();
					if(str != null) {
						failEmail = str;
					}
				} 
			} 
		}
		return failEmail;
	} 



	private String randomKey() {

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		uuid = uuid.substring(0, 10);

		return uuid;
	}




}

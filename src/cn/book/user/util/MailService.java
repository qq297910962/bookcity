package cn.book.user.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//�����ʼ��Ĺ�����
public class MailService {
	
	public static void sendMail(String recipient,String code) {
		/*
		 * 1.��ȡSession����
		 * 2.����һ�������ʼ��Ķ���Message
		 * 3.�����ʼ�Transport
		 */
		
		//1.��ȡ���Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("BookCityService@book.com","123");
			}	
		});
		
		//2.�����ʼ�����
		Message message = new MimeMessage(session);
		
		try {
			//���÷�����
			message.setFrom(new InternetAddress("BookCityService@book.com"));
			//�����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
			//�����ʼ�����
			message.setSubject("����������ǵļ����ʼ�");
			//�����ʼ�����,������ipconfig�鿴����ip��ַ
			String url = "http://localhost:8080/bookCity/user_active.action?code="+code;
			message.setContent("<h1>���ã����������������ɼ��</h1><h3><a href='" + url + "'>" + url + "</a></h3>","text/html;charset=UTF-8");
			//3.�����ʼ�
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		sendMail("aaa@book.com","xxwdwd");

	}
}

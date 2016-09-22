package com.gnahznib.email;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class SendMailTest {
	
	/**
	 * 发带有附件的邮件
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException 
	 */
	@Test
	public void fun2() throws AddressException, MessagingException, IOException {
		/*
		 * 1. 得到session
		 */
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.sina.com");//指定服务器名
		//props.setProperty("mail.smtp.auth", "true");//指定是否需要认证
		Session session = Session.getInstance(props);
		
		/*
		 * 2. 创建消息类，即MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);//创建消息对象
		msg.setFrom(new InternetAddress("xxxx@qq.com"));//设置发件人
		
		msg.setRecipients(RecipientType.TO, "yyyyy@qq.com");//设置收件人
		
		msg.setSubject("这是一封测试邮件！！！");//设置标题
		
		// msg.setContent("这是内容", "text/html;charset=utf-8");//设置正文，以及content-type头
		MimeMultipart partList = new MimeMultipart();//多部分邮件主体
		/*
		 * 向这个多部件邮件内容中添加一个一个部件
		 */
		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent("我是正文", "text/html;charset=utf-8");//这个部件是正文
		partList.addBodyPart(part1);//把部件添加部件集合中
		
		// 向多部件集合中添加一个部件，这个部件是附件
		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile(new File("D:\\123.doc"));//设置部件设置附件
		part2.setFileName(MimeUtility.encodeText("123.doc"));//设置附件的名称
		partList.addBodyPart(part2);
		
		msg.setContent(partList);//我们把多部分设置给消息类为邮件内容
		
		/*
		 * 3. 发邮件
		 */
		Transport tp = session.getTransport("smtp");//获取发送器
		// 登录服务器，需要用户名和密码
		tp.connect("XXXX", "xxxxxx");//提供用户名和密码连接服务器
		// 发
		tp.sendMessage(msg, msg.getAllRecipients());//发邮件
		
	}
}

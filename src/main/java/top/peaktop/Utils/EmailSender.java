package top.peaktop.Utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	/*程序默认的邮件发送服务,使用阿里云企业邮箱*/
	private final static String DefaultHost = "smtp.peaktop.top";
	/*邮箱认证信息*/
	private final static String Sender = "system@peaktop.top";
	private final static String SenderPsd = "Qq1320074071";
	private static Properties Props;
	private static String Port;
	private static Session session;
	private static MimeMessage message;
	private static String Subject = "账号验证";
	
	/* 初始化属性 */
	public static void InitProperties() {
		// 获取系统属性
		Props = System.getProperties();

		// 设置邮件服务器主机
		Props.setProperty("mail.smtp.host", DefaultHost);
		/* 设置邮件服务器端口号 */
		if (Port != null) {
			Props.setProperty("mail.smtp.port", Port);
		}
		// 用户认证
		Props.setProperty("mail.smtp.auth", "true");
		Props.setProperty("mail.user", Sender);
		Props.setProperty("mail.password", SenderPsd);
		/* 获取session认证 */
		session = Session.getDefaultInstance(Props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// 构建授权信息，用于进行SMTP进行身份验证
				return new PasswordAuthentication(Props.getProperty("mail.user"), Props.getProperty("mail.password")); // 发件人邮件用户名、密码
			}
		});
	}

	public static boolean sendEmail(String Contents,String Receiver) {
		if(Props == null){
			InitProperties();
		}
		try {
			// 创建默认的 MimeMessage 对象
			message = new MimeMessage(session);

			// Set From: 头部头字段,发送者邮箱
			message.setFrom(new InternetAddress(Sender));

			// Set To: 头部头字段,接收者邮箱
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Receiver));

			// Set Subject: 头部头字段,邮件标题
			message.setSubject(Subject);

			// 设置邮件内容
			message.setText(Contents);
			// 发送消息
			Transport.send(message);
			// System.out.println("Sent message successfully....from
			// w3cschool.cc");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}

package com.hehocom.hehocom.servicesImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.repository.MemberRepository;
import com.hehocom.hehocom.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public String checkEmail(String email) throws AddressException, MessagingException, IOException {
		Member member = memberRepository.findByEmail(email);

		if (member != null) {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("durupt.guillaume88@gmail.com", "Branlixemania88390");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("durupt.guillaume88@gmail.com", false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("durupt.guillaume88@gmail.com"));
			msg.setSubject("Mot de passe oublié");
			msg.setContent("Tutorials point email", "text/html");
			msg.setSentDate(new Date());

			String newPassword = generate(8);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					"<p>Bonjour, <br> Vous avez oublié votre mot de passe ? <br> <br> Je vous en ai refait un nouveau rien que pour vous :) </p> <br> <p> Mot de passe : <strong>"
							+ newPassword + "</strong></p><br> <p>Cordialement</p> <br> <p>Guillaume</p>"

					, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();

			// attachPart.attachFile("/var/tmp/image19.png");
			// multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);

			try {
				member.setPassword(this.encoder.encode(newPassword));
			} catch (Exception e) {
				e.getMessage();
				return null;
			}
			memberRepository.save(member);

			return "valid";
		} else {
			return "error";
		}

	}

	@Override
	public String contactMail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String generate(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres
																							// dont tu ne veux pas
		String pass = "";
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
			pass += chars.charAt(i);
		}
		System.out.println(pass);
		return pass;
	}

}

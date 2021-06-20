package com.hehocom.hehocom.services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

	public String checkEmail(String email) throws AddressException, MessagingException, IOException;

	public String contactMail();

}

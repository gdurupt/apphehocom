package com.hehocom.hehocom.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hehocom.hehocom.request.email.lostPassword;
import com.hehocom.hehocom.services.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class EmailController {

	@Autowired
	private EmailService emailService;

	// Email lostPassword
	@PostMapping(value = "/email/lostpassword")
	public String resetPassword(@RequestBody lostPassword lostPassword)
			throws AddressException, MessagingException, IOException {
		return emailService.checkEmail(lostPassword.getEmail());
	}

	// Email lostPassword
	@GetMapping(value = "/email/lostpassword")
	public String resetaPassword() {
		return "allo";
	}
}

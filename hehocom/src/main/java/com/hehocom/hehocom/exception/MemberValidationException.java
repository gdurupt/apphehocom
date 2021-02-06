package com.hehocom.hehocom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberValidationException extends ResponseStatusException {

	public MemberValidationException(String s) {
		super(HttpStatus.BAD_REQUEST, s);
	}

}

package com.hehocom.hehocom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConstraintViolationException extends ResponseStatusException {

	public ConstraintViolationException(String s) {
		super(HttpStatus.BAD_REQUEST, s);
	}

}

package com.study.app;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Parameter is invalid") // 404
public class BoardInvalidParameterException extends Exception {

	private static final long serialVersionUID = -1733917885620197467L;
	
	public BoardInvalidParameterException(String message) {
		super(message);
	}
	
}

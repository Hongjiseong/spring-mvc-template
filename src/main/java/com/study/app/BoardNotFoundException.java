package com.study.app;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Board Not Found") // 404
public class BoardNotFoundException extends Exception {

	private static final long serialVersionUID = -1733917885620197467L;

	public BoardNotFoundException(String message) {
		super(message);
	}
}

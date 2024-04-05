package com.todos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseStatusException  extends RuntimeException{
	
	public ResponseStatusException(String message) {
		super(message);
	}
	public ResponseStatusException(HttpStatus badRequest, String message) {
		super(message);
	}

}

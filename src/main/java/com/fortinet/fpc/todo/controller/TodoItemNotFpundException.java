package com.fortinet.fpc.todo.controller;

public class TodoItemNotFpundException extends RuntimeException {

	public TodoItemNotFpundException() {
	}

	public TodoItemNotFpundException(String message) {
		super(message);

	}

	public TodoItemNotFpundException(Throwable cause) {
		super(cause);
	}

	public TodoItemNotFpundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public TodoItemNotFpundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}

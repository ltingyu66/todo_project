package com.fortinet.fpc.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class TodoItemRestException {
	@ExceptionHandler
	public ResponseEntity<TodoItemErrorResponse> handleException(TodoItemNotFpundException exc){
		
		TodoItemErrorResponse error = new TodoItemErrorResponse(HttpStatus.NOT_FOUND.value(),
				exc.getMessage(), System.currentTimeMillis());
		
		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<TodoItemErrorResponse> handleException(Exception exc){
		
		TodoItemErrorResponse error = new TodoItemErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(), System.currentTimeMillis());
		
		
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}

}

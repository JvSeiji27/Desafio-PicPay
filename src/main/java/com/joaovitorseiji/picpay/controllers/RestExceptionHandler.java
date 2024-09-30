package com.joaovitorseiji.picpay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joaovitorseiji.picpay.exception.PicPayException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(PicPayException.class)	
	public ProblemDetail handlerPicPayException(PicPayException e) {
		return e.toProblemDetail();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		var fieldErrors = e.getFieldErrors()
						  .stream()
						  .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
						  .toList();
		
		var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		
		pb.setTitle("Your requestd parameters didn't validate.");
		pb.setProperty("invalid-params", fieldErrors);
		
		return pb;
	}
	
	private record InvalidParam(String name, String reason) {
		
	}
	
}

package com.joaovitorseiji.picpay.controllers;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joaovitorseiji.picpay.exception.PicPayException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(PicPayException.class)	
	public ProblemDetail handlerPicPayException(PicPayException e) {
		return e.toProblemDetail();
	}
	
}

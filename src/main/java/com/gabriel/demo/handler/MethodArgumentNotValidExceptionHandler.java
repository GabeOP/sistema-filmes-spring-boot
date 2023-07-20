package com.gabriel.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gabriel.demo.dto.ErrorDTO;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDTO handler(MethodArgumentNotValidException ex) {
		return new ErrorDTO("[ERROR] Complete all fields.");
	}
}

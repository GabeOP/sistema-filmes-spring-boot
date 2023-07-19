package com.gabriel.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gabriel.demo.dto.ErrorDTO;
import com.gabriel.demo.model.exception.EmptyFieldException;

@ControllerAdvice
public class EmptyFieldExceptionHandler {

	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ResponseBody
	@ExceptionHandler(EmptyFieldException.class)
	public ErrorDTO handler(EmptyFieldException ex) {
		return new ErrorDTO(ex.getMessage());
	}
}

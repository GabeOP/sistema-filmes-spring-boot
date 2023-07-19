package com.gabriel.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gabriel.demo.dto.ErrorDTO;
import com.gabriel.demo.model.exception.GenreNotFoundException;

@ControllerAdvice
public class GenreNotFoundExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(GenreNotFoundException.class)
	public ErrorDTO GenreNotFoundException(GenreNotFoundException ex) {
		return new ErrorDTO(ex.getMessage());
	}
}

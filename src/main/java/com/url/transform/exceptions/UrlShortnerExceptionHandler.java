package com.url.transform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class UrlShortnerExceptionHandler {
	@ExceptionHandler(value = UrlShortnerServiceException.class)
	public ResponseEntity<ErrorResponse> handlerForAppManagerServiceException(UrlShortnerServiceException ex) {

		if (ex.getStatusCode() == 0) {
			ex.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), ex.getStatusCode()),
				HttpStatus.valueOf(ex.getStatusCode()));
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerForResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND);
	}
}

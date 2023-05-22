package com.api.government.security.agency.lib.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.api.government.security.agency.lib.exception.impl.DataBaseException;
import com.api.government.security.agency.lib.exception.impl.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	static private HttpStatus notFound = HttpStatus.NOT_FOUND;
	static private HttpStatus badRequest = HttpStatus.BAD_REQUEST;
	static private HttpStatus unProcessable = HttpStatus.UNPROCESSABLE_ENTITY;


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception,
														HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setError("Resource not found.");
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(notFound).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException exception,
														HttpServletRequest request){
		StandardError err = new StandardError();

		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setError("DataBase exception.");
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(badRequest).body(err);
	}
}

package com.dango.dango.global.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dango.dango.global.common.response.ErrorResponse;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionManager {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder errorMessage = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorMessage.append(error.getDefaultMessage()).append("; ");
		}
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),errorMessage.toString()));
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex){
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
	}


	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex){
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
	}


}

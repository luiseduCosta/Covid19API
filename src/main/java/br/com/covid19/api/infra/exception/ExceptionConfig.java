package br.com.covid19.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionConfig {
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<?> badRequest(Exception ex) {
		return ResponseEntity
				.badRequest()
				.body(new Body(ex.getMessage()));
	}
	
	@ExceptionHandler({
		ObjectNotFoundException.class,
		NoSuchElementException.class
	})
	public ResponseEntity<?> notFound(Exception ex) {
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler({
		HttpRequestMethodNotSupportedException.class
	})
	public ResponseEntity<?> notAllowed(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new Body(ex.getMessage()));
	}
}


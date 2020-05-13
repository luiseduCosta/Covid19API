package br.com.covid19.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
		ObjectNotFoundException.class
	})
	public ResponseEntity<?> notFound(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new Body(ex.getMessage()));
	}
}


package com.todo.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<Object> handleResourceNotFoundException(
				ResourceNotFoundException ex, WebRequest request) {
			
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("status", HttpStatus.NOT_FOUND.value());
			body.put("error", "Não Encontrado");
			body.put("message", ex.getMessage());
			body.put("path", request.getDescription(false).replace("uri=", ""));
			
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, WebRequest request) {
			
			Map<String, String> errors = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("status", HttpStatus.BAD_REQUEST.value());
			body.put("error", "Erro de Validação");
			body.put("errors", errors);
			body.put("path", request.getDescription(false).replace("uri=", ""));
			
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}
		
}

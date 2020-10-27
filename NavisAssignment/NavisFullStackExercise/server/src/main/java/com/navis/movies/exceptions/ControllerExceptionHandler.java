package com.navis.movies.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse movieExceptionHandler(final MovieNotFoundException movieNotFoundException,
			final HttpServletRequest request) {

		ExceptionResponse errorResponse = new ExceptionResponse();

		errorResponse.setMessage(movieNotFoundException.getMessage());
		errorResponse.setRequestedUrl(request.getRequestURI());
		errorResponse.setException(movieNotFoundException.getClass().getName());
		errorResponse.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		return errorResponse;

	}

	
	@ExceptionHandler(SearchQueryNotFound.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleSearchQueryException(final SearchQueryNotFound exception,final HttpServletRequest request) {
		
		ExceptionResponse errorResponse = new ExceptionResponse();

		errorResponse.setMessage(exception.getMessage());
		errorResponse.setRequestedUrl(request.getRequestURI());
		errorResponse.setException(exception.getClass().getName());
		errorResponse.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		return errorResponse;
		
	}
	
	    
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleArgumentMismatchException(final MethodArgumentTypeMismatchException exception,final HttpServletRequest request) {
		
		ExceptionResponse errorResponse = new ExceptionResponse();

		errorResponse.setMessage("Argument Of given type is not matching : Id,index must be of number Type");
		errorResponse.setRequestedUrl(request.getRequestURI());
		errorResponse.setException(exception.getClass().getName());
		errorResponse.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		return errorResponse;
		
	}

	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleRunTimeException(final RuntimeException exception,final HttpServletRequest request) {
		
		ExceptionResponse errorResponse = new ExceptionResponse();

		errorResponse.setMessage(exception.getMessage());
		errorResponse.setRequestedUrl(request.getRequestURI());
		errorResponse.setException(exception.getClass().getName());
		errorResponse.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return errorResponse;
		
	}

	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,final HttpServletRequest request) {
		
		ExceptionResponse errorResponse = new ExceptionResponse();

		errorResponse.setMessage(exception.getMessage());
		errorResponse.setRequestedUrl(request.getRequestURI());
		errorResponse.setException(exception.getClass().getName());
		errorResponse.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return errorResponse;
		
	}
}

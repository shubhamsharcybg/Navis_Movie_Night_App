package com.navis.movies.exceptions;




import lombok.Data;

@Data
public class ExceptionResponse {
	
	private String timeStamp;
	private String message;
	private String status;
	private String requestedUrl;
	private String exception;
	
	
}

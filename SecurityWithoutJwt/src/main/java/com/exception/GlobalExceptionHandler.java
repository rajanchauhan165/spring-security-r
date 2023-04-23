package com.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setMessage(ce.getMessage());
		myErrorDetails.setDetails(wr.getDescription(false));
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception ce, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setMessage(ce.getMessage());
		myErrorDetails.setDetails(wr.getDescription(false));
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {
	
	
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	@ExceptionHandler({UnsupportedOperationException.class})
	public ResponseEntity<Error> handleUnsupportedOperationException(UnsupportedOperationException exception) {
		Error error = new Error(exception.getMessage(), HttpStatus.PRECONDITION_FAILED.value());
		return new ResponseEntity<Error>(error, HttpStatus.PRECONDITION_FAILED);
	}

}

class Error{
	private String errorMsg;
	private int httpstatusCode;
	
	public Error(String errorMsg, int httpStatusCode) {
		this.errorMsg = errorMsg;
		this.httpstatusCode = httpStatusCode;
	}
	public Error() {
		// TODO Auto-generated constructor stub
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getHttpstatusCode() {
		return httpstatusCode;
	}
	public void setHttpstatusCode(int httpstatusCode) {
		this.httpstatusCode = httpstatusCode;
	}
	
	
}
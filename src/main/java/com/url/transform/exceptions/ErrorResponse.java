package com.url.transform.exceptions;
/**
 * Error Response
 * @author Ankita Arya
 *
 */
public class ErrorResponse {
	/**
	 * the errorCode
	 */
	private int errorCode;
	/**
	 * the message 
	 */
	private String message;
	
	
	public ErrorResponse(String msg, int errorCode) {
		
		this.errorCode = errorCode;
		this.message = msg;
	}
	
	/**
	 * to instantiate error response
	 */
	public ErrorResponse() {		
	}
	
	/**
	 * gets the errorcode
	 * @return errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * sets the errorcode
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

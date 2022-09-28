package com.url.transform.exceptions;

public class UrlShortnerServiceException extends RuntimeException{
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public UrlShortnerServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UrlShortnerServiceException(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	private int statusCode;
	
	private String message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}

package com.tcs.boot.handler;

public class ErrorResponse {
    private String message;
    private Object details;
    
    public ErrorResponse() {}
    
    public ErrorResponse(String message, Object details) {
        this.message = message;
        this.details = details;
    }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
    
}

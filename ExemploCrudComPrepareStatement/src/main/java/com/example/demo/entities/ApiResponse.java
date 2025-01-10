package com.example.demo.entities;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 4701578409311784889L;
	private String message;
    private int status;
    
	public ApiResponse(String message, int status) {
		this.message = message;
		this.status = status;
	}
	
	public ApiResponse() {}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}

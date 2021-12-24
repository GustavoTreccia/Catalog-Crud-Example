package com.example.demo.exceptions;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6062087554455305469L;
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}

}

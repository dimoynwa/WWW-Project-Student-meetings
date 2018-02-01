package com.fmi.uni.sofia.www.errors;


public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823947L;
	
	private Object[] objects;
	
	public ValidationException() {
		super();
	}
	
	public ValidationException(Object[] objects) {
		this();
		this.objects = objects;
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

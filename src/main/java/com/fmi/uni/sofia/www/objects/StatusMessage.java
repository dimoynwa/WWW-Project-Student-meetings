package com.fmi.uni.sofia.www.objects;

public class StatusMessage {
	
	private String message;
	private Integer reference;
	private boolean status;
	
	
	
	public StatusMessage(String message, Integer reference, boolean status) {
		super();
		this.message = message;
		this.reference = reference;
		this.status = status;
	}

	public StatusMessage() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public static StatusMessage successStatusMessage(Integer ref) {
		return new StatusMessage("SUCCESS", ref, true);
	}
	
	public static StatusMessage errorStatusMessage(String message) {
		return new StatusMessage(message, null, false);
	}
	
}

package com.mindtree.atlantis.loyalty.core.dto;

public enum Status {
	
	SUCCESS("Success"), FAILURE("Failure");
	
	private String message;

	private Status(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}

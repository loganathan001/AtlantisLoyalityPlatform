package com.mindtree.atlantis.loyalty.core.constant;

public enum AtlantisErrorConstants {
	
	AUTHENTICATION_FAILED("ATL-401", "Authentication Failed"),
	INVALID_LOYALTY_POINT_ID("ATL-001", "Invalid Loyalty Point ID"),
	INVALID_INPUT_PARAMETER("ATL-003", "Invalid Input Parameter - %s"),
	UNKNOWN_ERROR("ATL-005", "Unknown Error"),
	MISSING_CONFIGURATION("ATL-006", "Missing Configuration: %s"),
	REST_ERROR("ATL-008", "REST Error - %s")
	
	;
	private String errorCode;
	private String errorMessage;

	private AtlantisErrorConstants(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getErrorMessage(Object... args) {
		return String.format(errorMessage, args);
	}
	
}

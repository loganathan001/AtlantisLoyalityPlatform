package com.mindtree.atlantis.loyalty.core.exception;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;

public class AtlantisBaseCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1732817970243065068L;
	
	private String errorCode;
	private String errorMessage;

	public AtlantisBaseCheckedException() {
		super();
	}

	public AtlantisBaseCheckedException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public AtlantisBaseCheckedException(AtlantisErrorConstants errorConstant) {
		this.errorCode = errorConstant.getErrorCode();
		this.errorMessage = errorConstant.getErrorMessage();
	}
	
	public AtlantisBaseCheckedException(String errorCode, String errorMessage, Object... args) {
		this.errorCode = errorCode;
		this.errorMessage = String.format(errorMessage, args);
	}
	
	public AtlantisBaseCheckedException(AtlantisErrorConstants errorConstant, Object... args) {
		this.errorCode = errorConstant.getErrorCode();
		this.errorMessage = errorConstant.getErrorMessage(args);
	}
	
	public AtlantisBaseCheckedException(String errorCode, String errorMessage, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public AtlantisBaseCheckedException(AtlantisErrorConstants errorConstant, Throwable cause) {
		super(cause);
		this.errorCode = errorConstant.getErrorCode();
		this.errorMessage = errorConstant.getErrorMessage();
	}
	
	public AtlantisBaseCheckedException(String errorCode, String errorMessage, Throwable cause, Object... args) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMessage = String.format(errorMessage, args);
	}
	
	public AtlantisBaseCheckedException(AtlantisErrorConstants errorConstant, Throwable cause, Object... args) {
		super(cause);
		this.errorCode = errorConstant.getErrorCode();
		this.errorMessage = errorConstant.getErrorMessage(args);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}

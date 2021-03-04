package com.mindtree.atlantis.loyalty.core.exception;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;

public class RestException extends AtlantisBaseCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4782366637093862526L;

	public RestException() {
	}

	public RestException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public RestException(AtlantisErrorConstants errorConstant) {
		super(errorConstant);
	}

	public RestException(String errorCode, String errorMessage, Object... args) {
		super(errorCode, errorMessage, args);
	}

	public RestException(AtlantisErrorConstants errorConstant, Object... args) {
		super(errorConstant, args);
	}

	public RestException(AtlantisErrorConstants errorConstant, Throwable cause, Object... args) {
		super(errorConstant, cause, args);
	}

	public RestException(AtlantisErrorConstants errorConstant, Throwable cause) {
		super(errorConstant, cause);
	}

	public RestException(String errorCode, String errorMessage, Throwable cause, Object... args) {
		super(errorCode, errorMessage, cause, args);
	}

	public RestException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}
	

}

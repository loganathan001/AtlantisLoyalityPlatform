package com.mindtree.atlantis.loyalty.core.exception;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;

public class AtlantisBusinessException extends AtlantisBaseCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3056359220926035211L;

	public AtlantisBusinessException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public AtlantisBusinessException(AtlantisErrorConstants errorConstant) {
		super(errorConstant);
	}

	public AtlantisBusinessException(String errorCode, String errorMessage, Object... args) {
		super(errorCode, errorMessage, args);
	}

	public AtlantisBusinessException(AtlantisErrorConstants errorConstant, Object... args) {
		super(errorConstant, args);
	}

	public AtlantisBusinessException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	public AtlantisBusinessException(AtlantisErrorConstants errorConstant, Throwable cause) {
		super(errorConstant, cause);
	}

	public AtlantisBusinessException(String errorCode, String errorMessage, Throwable cause, Object... args) {
		super(errorCode, errorMessage, cause, args);
	}

	public AtlantisBusinessException(AtlantisErrorConstants errorConstant, Throwable cause, Object... args) {
		super(errorConstant, cause, args);
	}

}

package com.mindtree.atlantis.loyalty.core.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.dto.ErrorDTO;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.util.ExceptionUtil;

@RestControllerAdvice
public class AtlantisExceptionHandler {
	
    Logger logger = LoggerFactory.getLogger(AtlantisExceptionHandler.class);


	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error(ExceptionUtil.getStackTraceAsString(ex));
		return buildErrorResponseEntity(new AtlantisBaseCheckedException(AtlantisErrorConstants.UNKNOWN_ERROR));
	}

	@ExceptionHandler(AtlantisBaseCheckedException.class)
	protected ResponseEntity<Object> handleAtlantisExceptions(AtlantisBaseCheckedException ex, WebRequest request) {
		logger.error(ExceptionUtil.getStackTraceAsString(ex));
		return buildErrorResponseEntity(ex);
	}

	private ResponseEntity<Object> buildErrorResponseEntity(AtlantisBaseCheckedException ex) {
		return new ResponseEntity<>(buildErrorResponse(ex), HttpStatus.OK);
	}

	private Object buildErrorResponse(AtlantisBaseCheckedException ex) {
		ResponseWrapper<Object> responseWrapper = new ResponseWrapper<Object>();
		List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
		errors.add(new ErrorDTO(ex.getErrorCode(), ex.getErrorMessage()));
		Throwable cause = ex.getCause();
		while(cause != null) {
			if(cause instanceof AtlantisBaseCheckedException) {
				AtlantisBaseCheckedException e = (AtlantisBaseCheckedException) cause;
				errors.add(new ErrorDTO(e.getErrorCode(), e.getErrorMessage()));
			}
			cause = cause.getCause();
		}
	
		responseWrapper.setErrors(errors);
		return responseWrapper;
	}
	
}

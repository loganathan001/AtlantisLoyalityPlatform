package com.mindtree.atlantis.loyalty.core.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ResponseWrapper<T> {
	
	private String id;

	private LocalDateTime responseTime;
	
	private T response;

	private List<ErrorDTO> errors;
}

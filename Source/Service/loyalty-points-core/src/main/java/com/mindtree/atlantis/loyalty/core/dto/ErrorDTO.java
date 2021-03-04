package com.mindtree.atlantis.loyalty.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
	
	private String errorCode;
	
	private String errorMessage;

}

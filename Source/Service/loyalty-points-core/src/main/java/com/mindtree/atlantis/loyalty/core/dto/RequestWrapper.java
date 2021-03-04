package com.mindtree.atlantis.loyalty.core.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RequestWrapper<T> {
	
	private String id;

	private LocalDateTime requestTime;
	
	@NonNull
	private T request;

}

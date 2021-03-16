package com.mindtree.atlantis.loyalty.core.dto.auth;

import lombok.Data;

@Data
public class TokenResponseDTO {
	
	private String accessToken;
	
	private String idToken;
	
	private int expiresInSecs;
	
}

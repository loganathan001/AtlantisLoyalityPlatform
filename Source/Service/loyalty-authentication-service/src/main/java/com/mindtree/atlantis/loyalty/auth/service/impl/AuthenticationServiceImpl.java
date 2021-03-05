package com.mindtree.atlantis.loyalty.auth.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.atlantis.loyalty.auth.integration.KeycloakManager;
import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthResponseDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private KeycloakManager keycloakManager;
	
	@Override
	public ResponseWrapper<? super ClientAuthResponseDTO> authenticateClient(RequestWrapper<? extends ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
		Map<?, ? extends Object> loginClient = keycloakManager.loginClient(requestDTO.getRequest());
		
		ClientAuthResponseDTO clientAuthResponse = new ClientAuthResponseDTO();
		clientAuthResponse.setAccessToken(String.valueOf(loginClient.get("access_token")));
		clientAuthResponse.setExpiresInSecs(String.valueOf(loginClient.get("expires_in")));
		ResponseWrapper<ClientAuthResponseDTO> responseWrapper = new ResponseWrapper<>();
		responseWrapper.setResponse(clientAuthResponse);
		return responseWrapper;
	}

}

package com.mindtree.atlantis.loyalty.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.atlantis.loyalty.auth.integration.KeycloakManager;
import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.StatusResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private KeycloakManager keycloakManager;
	
	@Override
	public ResponseWrapper<StatusResponseDTO> authenticateClient(RequestWrapper<ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
		StatusResponseDTO loginClient = keycloakManager.loginClient(requestDTO.getRequest());
		
		ResponseWrapper<StatusResponseDTO> responseWrapper = new ResponseWrapper<StatusResponseDTO>();
		responseWrapper.setResponse(loginClient);
		return responseWrapper;
	}

}

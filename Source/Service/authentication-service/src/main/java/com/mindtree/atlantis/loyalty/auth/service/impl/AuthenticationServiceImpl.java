package com.mindtree.atlantis.loyalty.auth.service.impl;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ACCESS_TOKEN;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.EXPIRES_IN;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_TOKEN;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.atlantis.loyalty.auth.integration.KeycloakManager;
import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.auth.TokenResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.UserAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.exception.RestException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private KeycloakManager keycloakManager;
	
//	@Override
//	public ResponseWrapper<TokenResponseDTO> authenticateClient(RequestWrapper<? extends ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
//		Map<?, ?> tokenResponseMap = keycloakManager.loginClient(requestDTO.getRequest());
//		
//		TokenResponseDTO tokenResponse = new TokenResponseDTO();
//		tokenResponse.setAccessToken(String.valueOf(tokenResponseMap.get(ACCESS_TOKEN)));
//		tokenResponse.setExpiresInSecs((int) tokenResponseMap.get(EXPIRES_IN));
//		ResponseWrapper<TokenResponseDTO> responseWrapper = new ResponseWrapper<>();
//		responseWrapper.setResponse(tokenResponse);
//		return responseWrapper;
//	}

	@Override
	public ResponseWrapper<TokenResponseDTO> authenticateUser(RequestWrapper<? extends UserAuthRequestDTO> requestDTO) throws RestException {
		Map<?, ?> tokenResponseMap = keycloakManager.loginUser(requestDTO.getRequest());
		
		TokenResponseDTO tokenResponse = new TokenResponseDTO();
		tokenResponse.setAccessToken(String.valueOf(tokenResponseMap.get(ACCESS_TOKEN)));
		tokenResponse.setIdToken(String.valueOf(tokenResponseMap.get(ID_TOKEN)));
		tokenResponse.setExpiresInSecs((int) tokenResponseMap.get(EXPIRES_IN));
		
		ResponseWrapper<TokenResponseDTO> responseWrapper = new ResponseWrapper<>();

		responseWrapper.setResponse(tokenResponse);
		return responseWrapper;
	}

	@Override
	public void logoutUser(String idToken) throws AtlantisBaseCheckedException {
		keycloakManager.logoutUser(idToken);
	}

}

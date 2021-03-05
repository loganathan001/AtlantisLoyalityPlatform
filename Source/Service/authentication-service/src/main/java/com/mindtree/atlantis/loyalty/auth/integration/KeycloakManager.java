package com.mindtree.atlantis.loyalty.auth.integration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.mindtree.atlantis.loyalty.core.config.AuthConfig;
import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.constant.RestAPI;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.UserAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.RestException;
import com.mindtree.atlantis.loyalty.core.util.RestUtil;

@Component
public class KeycloakManager {
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String ERROR_DESCRIPTION = "error_description";
	private static final String ERROR = "error";
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String GRANT_TYPE = "grant_type";
	private static final String GRANT_CLIENT_CREDENTIALS = "client_credentials";
	private static final String GRANT_PASSWORD = "password";
	
	@Autowired
	private RestUtil restUtil;
	
	@Autowired
	private AuthConfig authConfig;
	
	public Map<?, ?> loginClient(ClientAuthRequestDTO clientAuthRequestDto) throws RestException {
		MultiValueMap<String, String> clientLoginAttributes = new LinkedMultiValueMap<>();
		clientLoginAttributes.add(CLIENT_ID, clientAuthRequestDto.getClientId());
		clientLoginAttributes.add(CLIENT_SECRET, clientAuthRequestDto.getClientSecret());
		clientLoginAttributes.add(GRANT_TYPE, GRANT_CLIENT_CREDENTIALS);
		
		ResponseEntity<Map> responseEntity = restUtil.doHttpRequest(RestAPI.KEYCLOAK_CLIENT_LOGIN, null, clientLoginAttributes, Map.class);
		Map<?, ?> responseBody = responseEntity.getBody();
		if(responseBody.containsKey(ERROR)) {
			throw new RestException(AtlantisErrorConstants.AUTHENTICATION_FAILED, new Exception(responseBody.get(ERROR) + " : " + responseBody.get(ERROR_DESCRIPTION)));
		}
		return responseBody;
	}

	public Map<?, ?> loginUser(UserAuthRequestDTO userAuthRequestDto) throws RestException {
		MultiValueMap<String, String> userLoginAttributes = new LinkedMultiValueMap<>();
		ClientAuthRequestDTO endUserClient = authConfig.getEndUserClient();
		userLoginAttributes.add(CLIENT_ID, endUserClient.getClientId());
		userLoginAttributes.add(CLIENT_SECRET, endUserClient.getClientSecret());
		userLoginAttributes.add(USERNAME, userAuthRequestDto.getUsername());
		userLoginAttributes.add(PASSWORD, userAuthRequestDto.getPassword());
		userLoginAttributes.add(GRANT_TYPE, GRANT_PASSWORD);
		
		ResponseEntity<Map> responseEntity = restUtil.doHttpRequest(RestAPI.KEYCLOAK_USER_LOGIN, null, userLoginAttributes, Map.class);
		
		Map<?, ?> responseBody = responseEntity.getBody();
		if(responseBody.containsKey(ERROR)) {
			throw new RestException(AtlantisErrorConstants.AUTHENTICATION_FAILED, new Exception(responseBody.get(ERROR) + " : " + responseBody.get(ERROR_DESCRIPTION)));
		}
		return responseBody;
	}

}
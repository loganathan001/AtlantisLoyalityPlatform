package com.mindtree.atlantis.loyalty.auth.integration;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.CLIENT_ID;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.CLIENT_SECRET;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ERROR;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ERROR_DESCRIPTION;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.GRANT_CLIENT_CREDENTIALS;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.GRANT_PASSWORD;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.GRANT_TYPE;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_TOKEN_HINT;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.OPENID;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.PASSWORD;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.SCOPE;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.USERNAME;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		userLoginAttributes.add(SCOPE, OPENID);
		
		ResponseEntity<Map> responseEntity = restUtil.doHttpRequest(RestAPI.KEYCLOAK_USER_LOGIN, null, userLoginAttributes, Map.class);
		
		Map<?, ?> responseBody = responseEntity.getBody();
		if(responseBody.containsKey(ERROR)) {
			throw new RestException(AtlantisErrorConstants.AUTHENTICATION_FAILED, new Exception(responseBody.get(ERROR) + " : " + responseBody.get(ERROR_DESCRIPTION)));
		}
		return responseBody;
	}

	public void logoutUser(String idToken) throws RestException {
		Map<String,?> queryParams = Map.of(ID_TOKEN_HINT, idToken);
		ResponseEntity<?> responseEntity = restUtil.doHttpRequest(RestAPI.KEYCLOAK_USER_LOGOUT, queryParams, null, null);
		
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new RestException(AtlantisErrorConstants.AUTHENTICATION_FAILED, new Exception("Error in logout"));
		}
	}

}

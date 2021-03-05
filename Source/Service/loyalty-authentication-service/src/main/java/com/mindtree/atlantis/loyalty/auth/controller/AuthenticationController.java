package com.mindtree.atlantis.loyalty.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@RestController()
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/clientlogin")
	public ResponseWrapper<?> clientLogin(@RequestBody RequestWrapper<ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
		return authenticationService.authenticateClient(requestDTO);
	}
}

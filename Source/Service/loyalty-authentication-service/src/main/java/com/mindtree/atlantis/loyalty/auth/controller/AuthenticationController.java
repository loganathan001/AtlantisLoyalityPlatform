package com.mindtree.atlantis.loyalty.auth.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.Status;
import com.mindtree.atlantis.loyalty.core.dto.StatusResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.TokenResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.UserAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@RestController()
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/clienttoken")
	public ResponseWrapper<TokenResponseDTO> clientLogin(@RequestBody RequestWrapper<ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
		return authenticationService.authenticateClient(requestDTO);
	}
	
	@PostMapping("/userlogin")
	public ResponseEntity<ResponseWrapper<StatusResponseDTO>> userLogin(@RequestBody RequestWrapper<UserAuthRequestDTO> requestDTO, HttpServletResponse servletResponse) throws AtlantisBaseCheckedException {
		 ResponseWrapper<TokenResponseDTO> tokenResponseWrapper = authenticationService.authenticateUser(requestDTO);
		 
		 TokenResponseDTO tokenResponse = tokenResponseWrapper.getResponse();
		 ResponseWrapper<StatusResponseDTO> responseWrapper = new ResponseWrapper<>();
		 responseWrapper.setResponse(new StatusResponseDTO(Status.SUCCESS.name()));
		 
		 Cookie cookie = new Cookie("Authorization", tokenResponse.getAccessToken());
		 cookie.setMaxAge(tokenResponse.getExpiresInSecs());
		 servletResponse.addCookie(cookie);
		 
		 return new ResponseEntity<ResponseWrapper<StatusResponseDTO>>(responseWrapper, HttpStatus.OK);
	}
}

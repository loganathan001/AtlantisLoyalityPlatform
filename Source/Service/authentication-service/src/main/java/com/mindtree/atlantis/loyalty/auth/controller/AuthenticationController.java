package com.mindtree.atlantis.loyalty.auth.controller;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.COOKIE_HEADER_AUTHORIZATION;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.COOKIE_HEADER_ID_TOKEN;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.Status;
import com.mindtree.atlantis.loyalty.core.dto.StatusResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.TokenResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.UserAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;
import com.mindtree.atlantis.loyalty.core.spi.auth.AuthenticationService;

@RestController()
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

//	@PostMapping("/clienttoken")
//	public ResponseWrapper<TokenResponseDTO> clientLogin(@RequestBody RequestWrapper<ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException {
//		return authenticationService.authenticateClient(requestDTO);
//	}

	@PostMapping("/user/login")
	public ResponseEntity<ResponseWrapper<StatusResponseDTO>> userLogin(
			@RequestBody RequestWrapper<UserAuthRequestDTO> requestDTO, HttpServletResponse servletResponse)
			throws AtlantisBaseCheckedException {
		ResponseWrapper<TokenResponseDTO> tokenResponseWrapper = authenticationService.authenticateUser(requestDTO);

		TokenResponseDTO tokenResponse = tokenResponseWrapper.getResponse();
		ResponseWrapper<StatusResponseDTO> responseWrapper = new ResponseWrapper<>();
		responseWrapper.setResponse(new StatusResponseDTO(Status.SUCCESS.name()));

		Cookie authTokenCookie = new Cookie(COOKIE_HEADER_AUTHORIZATION, tokenResponse.getAccessToken());
		authTokenCookie.setMaxAge(tokenResponse.getExpiresInSecs());
		servletResponse.addCookie(authTokenCookie);

		Cookie idTokenCookie = new Cookie(COOKIE_HEADER_ID_TOKEN, tokenResponse.getIdToken());
		idTokenCookie.setMaxAge(tokenResponse.getExpiresInSecs());
		servletResponse.addCookie(idTokenCookie);

		return new ResponseEntity<ResponseWrapper<StatusResponseDTO>>(responseWrapper, HttpStatus.OK);
	}

	@GetMapping("/user/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response)
			throws AtlantisBaseCheckedException {
		String idToken = WebUtils.getCookie(request, COOKIE_HEADER_ID_TOKEN).getValue();
		authenticationService.logoutUser(idToken);

		Cookie authTokenCookie = new Cookie(COOKIE_HEADER_AUTHORIZATION, null);
		authTokenCookie.setMaxAge(0);
		response.addCookie(authTokenCookie);

		Cookie idTokenCookie = new Cookie(COOKIE_HEADER_ID_TOKEN, null);
		idTokenCookie.setMaxAge(0);
		response.addCookie(idTokenCookie);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}

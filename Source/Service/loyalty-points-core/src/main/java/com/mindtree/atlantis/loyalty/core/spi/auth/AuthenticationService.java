package com.mindtree.atlantis.loyalty.core.spi.auth;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.TokenResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.UserAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;

public interface AuthenticationService {
	
	public ResponseWrapper<TokenResponseDTO> authenticateClient(RequestWrapper<? extends ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException;

	public ResponseWrapper<TokenResponseDTO> authenticateUser(RequestWrapper<? extends UserAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException;

}

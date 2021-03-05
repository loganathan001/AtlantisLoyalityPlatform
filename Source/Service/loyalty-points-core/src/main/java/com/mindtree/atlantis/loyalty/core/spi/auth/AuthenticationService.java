package com.mindtree.atlantis.loyalty.core.spi.auth;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthResponseDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;

public interface AuthenticationService {
	
	public ResponseWrapper<? super ClientAuthResponseDTO> authenticateClient(RequestWrapper<? extends ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException;

}

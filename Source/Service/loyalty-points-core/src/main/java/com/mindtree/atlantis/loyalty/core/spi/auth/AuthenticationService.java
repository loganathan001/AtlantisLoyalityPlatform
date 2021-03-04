package com.mindtree.atlantis.loyalty.core.spi.auth;

import com.mindtree.atlantis.loyalty.core.dto.RequestWrapper;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.StatusResponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBaseCheckedException;

public interface AuthenticationService {
	
	public ResponseWrapper<StatusResponseDTO> authenticateClient(RequestWrapper<ClientAuthRequestDTO> requestDTO) throws AtlantisBaseCheckedException;

}

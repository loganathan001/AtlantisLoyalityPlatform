package com.mindtree.atlantis.loyalty.points.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBusinessException;
import com.mindtree.atlantis.loyalty.core.spi.points.LoyaltyPointsService;
import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointEntity;
import com.mindtree.atlantis.loyalty.points.repository.LoyaltyPointRepository;

@Component
public class LoyaltyPointsServiceImpl implements LoyaltyPointsService {
	
	@Autowired
	private LoyaltyPointRepository loyaltyPointRepository;

	@Override
	public LoyaltyPointsDTO getLoyaltyPoints(String loyaltyid) throws AtlantisBusinessException {
		Optional<LoyaltyPointEntity> loyaltyPointEntityOpt = loyaltyPointRepository.findByLoyaltyPointId(loyaltyid);
		if(loyaltyPointEntityOpt.isPresent()) {
			LoyaltyPointEntity loyaltyPointEntity = loyaltyPointEntityOpt.get();
			LoyaltyPointsDTO loyaltyPointsDTO = new LoyaltyPointsDTO();
			loyaltyPointsDTO.setLoyaltyPoints(loyaltyPointEntity.getAccruedPoints());
			return loyaltyPointsDTO;
		} else {
			throw new AtlantisBusinessException(AtlantisErrorConstants.INVALID_LOYALTY_POINT_ID);
		}
	}

	@Override
	public TransactionsDTO getTransactions(String loyaltyid, String[] transactionTypes) throws AtlantisBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO purchasePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO acquirePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO redeemPoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}

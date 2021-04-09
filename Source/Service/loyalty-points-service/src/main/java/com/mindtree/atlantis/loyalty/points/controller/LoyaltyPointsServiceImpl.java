package com.mindtree.atlantis.loyalty.points.controller;

import org.springframework.stereotype.Component;

import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;
import com.mindtree.atlantis.loyalty.core.spi.auth.LoyaltyPointsService;

@Component
public class LoyaltyPointsServiceImpl implements LoyaltyPointsService {

	@Override
	public LoyaltyPointsDTO getLoyaltyPoints(String loyaltyid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionsDTO getTransactions(String loyaltyid, Integer transactionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO purchasePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO acquirePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointsTransactionReponseDTO redeemPoints(PointsTransactionRequestDTO purchasePointsRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}

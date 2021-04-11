package com.mindtree.atlantis.loyalty.points.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBusinessException;
import com.mindtree.atlantis.loyalty.core.spi.points.LoyaltyPointsService;
import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointEntity;
import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointTransactionEntity;
import com.mindtree.atlantis.loyalty.points.repository.LoyaltyPointRepository;
import com.mindtree.atlantis.loyalty.points.repository.LoyaltyPointTransactionRepository;

@Component
public class LoyaltyPointsServiceImpl implements LoyaltyPointsService {
	
	private static final int DEFAULT_PAGE_START_INDEX = 0;

	private static final int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	private LoyaltyPointRepository loyaltyPointRepository;
	
	@Autowired
	private LoyaltyPointTransactionRepository loyaltyPointTransactionRepository;

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
	public TransactionsDTO getTransactions(String loyaltyid, String[] transactionTypes, Integer pageStart, Integer pageFetch) throws AtlantisBusinessException {
		Pageable pageable = getPageableForTransactions(pageStart, pageFetch);
		List<LoyaltyPointTransactionEntity> loyaltyPointTransactionsEntities = loyaltyPointTransactionRepository.findByLoyaltyPointIdAndTransactionTypeInOrderByCrDtimesAsc(loyaltyid, Arrays.asList(transactionTypes), pageable);
		TransactionsDTO transactionsDTO = new TransactionsDTO();
		List<TransactionDTO> transactionDtoList = 
				loyaltyPointTransactionsEntities.stream()
						.map(entity -> {
							TransactionDTO transactionDTO = new TransactionDTO();
							transactionDTO.setBalanceLoyaltyPoints(entity.getBalancePoints());
							transactionDTO.setTransactedLoyaltyPoints(entity.getPoints());
							transactionDTO.setTransactionDescription(entity.getTransactionDescription());
							transactionDTO.setTransactionId(entity.getLoyaltyPointTransactionId());
							transactionDTO.setTransactionTimestamp(entity.getCrDtimes());
							transactionDTO.setTransactionType(entity.getTransactionType());
							return transactionDTO;
						})
						.collect(Collectors.toList());
		transactionsDTO.setTransactions(transactionDtoList);
		return transactionsDTO;
	}

	private Pageable getPageableForTransactions(Integer pageStart, Integer pageSize) throws AtlantisBusinessException {
		if(pageStart == null && pageSize == null) {
			return null;
		}
		
		int pageStartIndex;
		if(pageStart == null) {
			pageStartIndex = DEFAULT_PAGE_START_INDEX;
		} else {
			if(pageStart.intValue() > 0) {
				pageStartIndex = pageStart.intValue() - 1;
			} else {
				throw new AtlantisBusinessException(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorCode(),
						String.format(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorMessage(), "pageStart"));
			}
		}
		
		int pageSizeVal;
		if(pageSize == null) {
			pageSizeVal = DEFAULT_PAGE_SIZE;
		} else {
			if(pageSize.intValue() > 0) {
				pageSizeVal = pageSize.intValue();
			} else {
				throw new AtlantisBusinessException(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorCode(),
						String.format(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorMessage(), "pageStart"));
			}
		}
		
		return PageRequest.of(pageStartIndex, pageSizeVal);
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

package com.mindtree.atlantis.loyalty.points.controller;

import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_ATLANTIS_LOYALTY_ACQUIRE_POINTS;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_ATLANTIS_LOYALTY_PURCHASE_POINTS;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_ATLANTIS_LOYALTY_READ_POINTS;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_ATLANTIS_LOYALTY_READ_TRANSACTIONS;
import static com.mindtree.atlantis.loyalty.core.constant.ALPConstants.ID_ATLANTIS_LOYALTY_REDEEM_POINTS;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.atlantis.loyalty.core.constant.AtlantisErrorConstants;
import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionType;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBusinessException;
import com.mindtree.atlantis.loyalty.core.spi.points.LoyaltyPointsService;

@RestController
public class ALPController {
	
	@Autowired
	private LoyaltyPointsService loyaltyPointsService;

	@GetMapping(value = "/loyaltypoints/loyaltyid/{loyaltyid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<LoyaltyPointsDTO> getLoyaltyPoints(@PathVariable("loyaltyid") String loyaltyid) throws AtlantisBusinessException {
		return createResponseWrapper(loyaltyPointsService.getLoyaltyPoints(loyaltyid), ID_ATLANTIS_LOYALTY_READ_POINTS);
	}

	@GetMapping(value = "/transactions/loyaltyid/{loyaltyid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<TransactionsDTO> getTransactions(@PathVariable("loyaltyid") String loyaltyid,
			@RequestParam(name = "type", required = false) String[] transactionTypes,
			@RequestParam(name = "pageStart", required = false) Integer pageStart,
			@RequestParam(name = "pageSize", required = false) Integer pageSize
			) throws AtlantisBusinessException {
		String[] resolveTransactionTypes = TransactionType.resolveTransactionTypes(transactionTypes);
		if(resolveTransactionTypes.length > 0) {
			return createResponseWrapper(loyaltyPointsService.getTransactions(loyaltyid, resolveTransactionTypes, pageStart, pageSize), ID_ATLANTIS_LOYALTY_READ_TRANSACTIONS);
		} else {
			throw new AtlantisBusinessException(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorCode(), 
					String.format(AtlantisErrorConstants.INVALID_INPUT_PARAMETER.getErrorMessage(), "'type' query parameter"));
		}
	}

	@PostMapping(value = "/loyaltypoints/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> purchasePoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		return createResponseWrapper(loyaltyPointsService.purchasePoints(purchasePointsRequestDTO), ID_ATLANTIS_LOYALTY_PURCHASE_POINTS);
	}

	@PostMapping(value = "/loyaltypoints/acquire", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> acquirePoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		return createResponseWrapper(loyaltyPointsService.acquirePoints(purchasePointsRequestDTO), ID_ATLANTIS_LOYALTY_ACQUIRE_POINTS);
	}

	@PostMapping(value = "/loyaltypoints/redeem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> redeemPoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException {
		return createResponseWrapper(loyaltyPointsService.redeemPoints(purchasePointsRequestDTO), ID_ATLANTIS_LOYALTY_REDEEM_POINTS);
	}
	
	private <T> ResponseWrapper<T> createResponseWrapper(T response, String id) {
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
		responseWrapper.setId(id);
		responseWrapper.setResponse(response);
		responseWrapper.setResponseTime(LocalDateTime.now());
		return responseWrapper;
	}

}

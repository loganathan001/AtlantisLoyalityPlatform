package com.mindtree.atlantis.loyalty.points.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.atlantis.loyalty.core.dto.ResponseWrapper;
import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;

@RestController
public class ALPController {

	@GetMapping(value = "/loyaltypoints/loyaltyid/{loyaltyid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<LoyaltyPointsDTO> getLoyaltyPoints(@PathVariable("loyaltyid") String loyaltyid) {
		return null;
	}

	@GetMapping(value = "/transactions/loyaltyid/{loyaltyid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<TransactionsDTO> getTransactions(@PathVariable("loyaltyid") String loyaltyid,
			@RequestParam(name = "type", required = false) Integer transactionType) {
		return null;
	}

	@PostMapping(value = "/loyaltypoints/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> purchasePoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) {
		return null;
	}

	@PostMapping(value = "/loyaltypoints/acquire", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> acquirePoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) {
		return null;
	}

	@PostMapping(value = "/loyaltypoints/redeem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<PointsTransactionReponseDTO> redeemPoints(
			@RequestBody PointsTransactionRequestDTO purchasePointsRequestDTO) {
		return null;
	}

}

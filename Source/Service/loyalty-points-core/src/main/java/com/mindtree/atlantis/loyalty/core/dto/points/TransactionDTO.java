package com.mindtree.atlantis.loyalty.core.dto.points;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDTO {

	private  String transactionId;
	private  String transactionType;
	private  String transactionDescription;
	private  LocalDateTime transactionTimestamp;
	private  long transactedLoyaltyPoints;
	private  long balanceLoyaltyPoints;

}

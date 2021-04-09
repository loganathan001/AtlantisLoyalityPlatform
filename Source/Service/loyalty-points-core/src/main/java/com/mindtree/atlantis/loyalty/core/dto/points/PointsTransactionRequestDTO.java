package com.mindtree.atlantis.loyalty.core.dto.points;

import lombok.Data;

@Data
public class PointsTransactionRequestDTO {

	private String loyaltyId;
	private long loyaltyPoints;
	private String transactionId;

}

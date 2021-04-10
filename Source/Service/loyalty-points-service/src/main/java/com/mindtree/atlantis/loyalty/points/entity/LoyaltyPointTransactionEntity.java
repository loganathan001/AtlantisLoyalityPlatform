package com.mindtree.atlantis.loyalty.points.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "loyalty_point_transactions")
@Data
public class LoyaltyPointTransactionEntity {
	
	@Id
	@Field(name="loyalty_point_transaction_id")
	private String loyaltyPointTransactionId;
	
	@Field(name="transaction_description")
	private String transactionDescription;
	
	@Field(name="transaction_type")
	private String transactionType;
	
	@Field(name="loyalty_point_id")
	private String loyaltyPointId;

	@Field(name="points")
	private String points;
	
	@Field(name="balance_points")
	private String balancePoints;
	
	@Field(name="status")
	private String status;
	
	@Field(name="cr_dtime")
	private LocalDateTime crDtimes;
	
	@Field(name="cr_by")
	private String crBy;
	
	@Field(name="upd_dtime")
	private LocalDateTime updDtimes;
	
	@Field(name="upd_by")
	private String updBy;
	
	@Field(name="is_deleted")
	private boolean isDeleted;
	
	@Field(name="deleted_dtimes")
	private LocalDateTime deletedDtimes;
	
}

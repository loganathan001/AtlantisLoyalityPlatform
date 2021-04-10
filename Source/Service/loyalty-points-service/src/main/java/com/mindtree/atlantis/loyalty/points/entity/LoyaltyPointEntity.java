package com.mindtree.atlantis.loyalty.points.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "loyalty_points")
@Data
public class LoyaltyPointEntity {
	
	@Field("loyalty_point_id")
	private String loyaltyPointId;

	@Field("accrued_points")
	private String accruedPoints;
	
	@Field("cr_dtime")
	private LocalDateTime crDtimes;
	
	@Field("cr_by")
	private String crBy;
	
	@Field("upd_dtime")
	private LocalDateTime updDtimes;
	
	@Field("upd_by")
	private String updBy;
	
	@Field("is_deleted")
	private boolean isDeleted;
	
	@Field("deleted_dtimes")
	private LocalDateTime deletedDtimes;
	
}

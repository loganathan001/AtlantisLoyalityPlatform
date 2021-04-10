package com.mindtree.atlantis.loyalty.points.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "loyalty_point_price")
@Data
public class LoyaltyPointPriceEntity {
	
	@Id
	@Field(name="price")
	private int price;

	@Field(name="currency")
	private String currecy;
	
	@Field(name="upd_dtime")
	private LocalDateTime updDtimes;
	
	@Field(name="upd_by")
	private String updBy;
	
}

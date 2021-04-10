package com.mindtree.atlantis.loyalty.points.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointPriceEntity;

@Repository
public interface LoyaltyPointPriceRepository extends MongoRepository<LoyaltyPointPriceEntity, String> {
}
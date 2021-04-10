package com.mindtree.atlantis.loyalty.points.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointEntity;

@Repository
public interface LoyaltyPointRepository extends MongoRepository<LoyaltyPointEntity, String> {
	
	Optional<LoyaltyPointEntity> findByLoyaltyPointId(String loyaltyPointId);
	
}
package com.mindtree.atlantis.loyalty.points.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.atlantis.loyalty.points.entity.LoyaltyPointTransactionEntity;

@Repository
public interface LoyaltyPointTransactionRepository extends MongoRepository<LoyaltyPointTransactionEntity, String> {
	
	public List<LoyaltyPointTransactionEntity> findByLoyaltyPointIdAndTransactionTypeInOrderByCrDtimesAsc(String loyaltyPointId, List<String> transactionTypes, Pageable pagaeable);
}
package com.mindtree.atlantis.loyalty.core.spi.auth;

import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;

/**
 * The Interface LoyaltyPointsService.
 */
public interface LoyaltyPointsService {

	/**
	 * Gets the loyalty points.
	 *
	 * @param loyaltyid the loyaltyid
	 * @return the loyalty points
	 */
	public LoyaltyPointsDTO getLoyaltyPoints(String loyaltyid);

	/**
	 * Gets the transactions.
	 *
	 * @param loyaltyid the loyaltyid
	 * @param transactionType the transaction type
	 * @return the transactions
	 */
	public TransactionsDTO getTransactions(String loyaltyid, Integer transactionType);

	/**
	 * Purchase points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO purchasePoints(PointsTransactionRequestDTO purchasePointsRequestDTO);

	/**
	 * Acquire points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO acquirePoints(PointsTransactionRequestDTO purchasePointsRequestDTO);

	/**
	 * Redeem points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO redeemPoints(PointsTransactionRequestDTO purchasePointsRequestDTO);
}

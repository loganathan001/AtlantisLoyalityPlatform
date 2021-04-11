package com.mindtree.atlantis.loyalty.core.spi.points;

import com.mindtree.atlantis.loyalty.core.dto.points.LoyaltyPointsDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionReponseDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.PointsTransactionRequestDTO;
import com.mindtree.atlantis.loyalty.core.dto.points.TransactionsDTO;
import com.mindtree.atlantis.loyalty.core.exception.AtlantisBusinessException;

/**
 * The Interface LoyaltyPointsService.
 */
public interface LoyaltyPointsService {

	/**
	 * Gets the loyalty points.
	 *
	 * @param loyaltyid the loyaltyid
	 * @return the loyalty points
	 * @throws AtlantisBusinessException 
	 */
	public LoyaltyPointsDTO getLoyaltyPoints(String loyaltyid) throws AtlantisBusinessException;

	/**
	 * Gets the transactions.
	 *
	 * @param loyaltyid the loyaltyid
	 * @param transactionType the transaction type
	 * @return the transactions
	 */
	public TransactionsDTO getTransactions(String loyaltyid, String[] transactionType, Integer pageStart, Integer pageFetch) throws AtlantisBusinessException;

	/**
	 * Purchase points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO purchasePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException;

	/**
	 * Acquire points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO acquirePoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException;

	/**
	 * Redeem points.
	 *
	 * @param purchasePointsRequestDTO the purchase points request DTO
	 * @return the points transaction reponse DTO
	 */
	public PointsTransactionReponseDTO redeemPoints(PointsTransactionRequestDTO purchasePointsRequestDTO) throws AtlantisBusinessException;

}

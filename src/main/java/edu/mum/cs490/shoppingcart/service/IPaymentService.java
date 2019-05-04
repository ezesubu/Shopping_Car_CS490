package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.TransactionType;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface IPaymentService {
    /**
     *
     * @param txnId
     * @param srcCardNo
     * @param expirationDate
     * @param nameOnCard
     * @param CVV
     * @param zipCode
     * @param cardType
     * @param amount
     * @param dstCardNo
     * @param transactionType
     * @return 1 - success, 2 - not found error, 3 - not enough money, 500 - external system error, 999 - connection error
     */
    Integer doTransaction(String txnId, String srcCardNo, String expirationDate, String nameOnCard, String CVV, String zipCode, String cardType, Double amount, String dstCardNo, TransactionType transactionType);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.shoppingcart.aop;

import edu.mum.cs490.shoppingcart.domain.Transaction;
import edu.mum.cs490.shoppingcart.domain.TransactionType;
import edu.mum.cs490.shoppingcart.repository.TransactionRepository;
import edu.mum.cs490.shoppingcart.utility.AESConverterUtility;
import edu.mum.cs490.shoppingcart.utility.DoubleRoundUpUtility;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Aspect
@Component
public class TransactionAOPService {

    @Autowired
    private AESConverterUtility aesConverter;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * the method is called between OSS and ExternalTransactionAPI
     *
     * @param pjp
     * @param data
     * @return
     * @throws Throwable
     */
    @Around("execution(* edu.mum.cs490.shoppingcart.utility.HttpSenderUtility.doPostTransactionToApi(..))&& args(data)")
    public Object aopEncryptDecryptService(ProceedingJoinPoint pjp, String data) throws Throwable {
//        System.out.println("# AOP BEFORE (5) #  is called on " + pjp.getSignature().toShortString() + " " + data);
        String encryptedData;
        try {
            encryptedData = aesConverter.encrypt(data);
        } catch (Exception e) {
            encryptedData = e.getMessage();
        }
        System.out.println("encrypted data - " + (encryptedData != null ? encryptedData : null));
        Object retVal = pjp.proceed(new Object[]{encryptedData});
//        System.out.println("# AOP AFTER (5) #  is called on " + pjp.getSignature().toShortString() + " returnValue - " + (retVal != null ? retVal.toString() : null));
        retVal = aesConverter.decrypt(retVal != null ? retVal.toString() : "null");
        System.out.println("decrypted result - " + retVal);
        return retVal;
    }


    @Around("execution(* edu.mum.cs490.shoppingcart.service.IPaymentService.doTransaction(..))&& args(txnId, srcCardNo, expirationDate, nameOnCard, CVV, zipCode, cardType, amount, dstCardNo, transactionType)")
    public Object aopInternalEncryptDecryptService(ProceedingJoinPoint pjp, String txnId, String srcCardNo, String expirationDate, String nameOnCard, String CVV, String zipCode, String cardType, Double amount, String dstCardNo, TransactionType transactionType) throws Throwable {
        Transaction transaction = new Transaction(srcCardNo, cardType, dstCardNo, DoubleRoundUpUtility.roundUp(amount), "", txnId, transactionType);
        try {
            srcCardNo = aesConverter.decrypt(srcCardNo);
//            cardType = aesConverter.decrypt(cardType);
            expirationDate = aesConverter.decrypt(expirationDate);
            nameOnCard = aesConverter.decrypt(nameOnCard).toUpperCase();
            CVV = aesConverter.decrypt(CVV);
            dstCardNo = aesConverter.decrypt(dstCardNo);
            zipCode = aesConverter.decrypt(zipCode);
        } catch (Exception e) {
            srcCardNo = e.getMessage();
            System.err.println(e.getMessage());
        }
        System.out.println("decrypted data - " + txnId + " " + srcCardNo + " " + expirationDate + " " + nameOnCard + " " + CVV + " " + zipCode + " " + cardType + " " + amount + " " + dstCardNo);
        Object retVal = pjp.proceed(new Object[]{txnId, srcCardNo, expirationDate, nameOnCard, CVV, zipCode, cardType, DoubleRoundUpUtility.roundUp(amount), dstCardNo, transactionType});
        transaction.setResult(retVal.toString());
        transactionRepository.save(transaction);
        return retVal;
    }
}

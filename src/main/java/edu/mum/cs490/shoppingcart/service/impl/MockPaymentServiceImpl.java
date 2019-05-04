package edu.mum.cs490.shoppingcart.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.mum.cs490.shoppingcart.domain.TransactionType;
import edu.mum.cs490.shoppingcart.model.payment.mock.TransactionRequest;
import edu.mum.cs490.shoppingcart.service.IPaymentService;
import edu.mum.cs490.shoppingcart.utility.AESConverterUtility;
import edu.mum.cs490.shoppingcart.utility.DoubleRoundUpUtility;
import edu.mum.cs490.shoppingcart.utility.HttpSenderUtility;
import edu.mum.cs490.shoppingcart.utility.JsonConverterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidParameterException;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service
public class MockPaymentServiceImpl implements IPaymentService {

    @Autowired
    private HttpSenderUtility httpSender;

    @Autowired
    private AESConverterUtility aesConverter;



    @Override
    public Integer doTransaction(String txnId, String srcCardNo, String expirationDate, String nameOnCard, String CVV, String zipCode, String cardType, Double amount, String dstCardNo, TransactionType transactionType) {

        System.out.println("IPaymentService template method is called.");

        TransactionRequest transactionRequest = new TransactionRequest(txnId, srcCardNo, expirationDate, nameOnCard.toUpperCase(), CVV, zipCode, cardType.toUpperCase(), DoubleRoundUpUtility.roundUp(amount), dstCardNo);

        System.out.println("Request data : " + transactionRequest.toString());

        String requestData;
        try {
            requestData = JsonConverterUtility.<TransactionRequest>objectToJson(transactionRequest);
        } catch (JsonProcessingException e) {
            throw new InvalidParameterException("Invalid parameter!");
        }
        Integer responseCode;
        try {
            String responseData = httpSender.doPostTransactionToApi(requestData);
            try {
                responseCode = Integer.parseInt(responseData);
            } catch (NumberFormatException e) {
                System.err.println("External system error! " + responseData);
                responseCode = 500;
            }
        } catch (IOException e) {
            System.err.println("Connection error! " + e.getMessage());
            responseCode = 999;
        }

        System.out.println("Result of template method is " + responseCode);
        return responseCode;
    }
}

package edu.mum.cs490.shoppingcart.controller;

import edu.mum.cs490.shoppingcart.domain.TransactionType;
import edu.mum.cs490.shoppingcart.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Controller
@RequestMapping("mock")
public class MockPaymentController {

    @Autowired
    private IPaymentService paymentService;

    /**
     * localhost:8080/mock/1/01234567890123456/2019/TEST/52557/500/1/CVV
     * @param txnId
     * @param srcCardNo
     * @param expirationDate
     * @param nameOnCard
     * @param zipCode
     * @param amount
     * @param dstCardNo
     * @param cvv
     * @return
     */
    @GetMapping("/{txnId}/{srcCardNo}/{expirationMonth}/{expirationDate}/{nameOnCard}/{zipCode}/{cardType}/{amount}/{dstCardNo}/{cvv}")
    public String getAllProduct(@PathVariable(value="txnId") String txnId,
                                @PathVariable(value="srcCardNo") String srcCardNo,
                                @PathVariable(value="expirationMonth") String expirationMonth,
                                @PathVariable(value="expirationDate") String expirationDate,
                                @PathVariable(value="nameOnCard") String nameOnCard,
                                @PathVariable(value="zipCode") String zipCode,
                                @PathVariable(value="cardType") String cardType,
                                @PathVariable(value="amount") String amount,
                                @PathVariable(value="dstCardNo") String dstCardNo,
                                @PathVariable(value="cvv") String cvv){
        System.out.println(txnId);
        System.out.println("Result of payment is " + paymentService.doTransaction(txnId,srcCardNo,expirationMonth + "/" + expirationDate,nameOnCard, cvv, zipCode, cardType, Double.parseDouble(amount),dstCardNo, TransactionType.PURCHASE));
        return "mock";
    }
}

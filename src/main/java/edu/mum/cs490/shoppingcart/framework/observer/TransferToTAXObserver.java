package edu.mum.cs490.shoppingcart.framework.observer;

import edu.mum.cs490.shoppingcart.domain.CardDetail;
import edu.mum.cs490.shoppingcart.domain.Order;
import edu.mum.cs490.shoppingcart.domain.TransactionType;
import edu.mum.cs490.shoppingcart.service.PaymentService;

import java.util.Observable;
import java.util.Observer;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class TransferToTAXObserver implements Observer {

    private final Order order;

    private final CardDetail OSSCardDetail;

    private final CardDetail taxCardDetail;

    private final PaymentService paymentService;

    public TransferToTAXObserver(Order order, CardDetail OSSCardDetail, CardDetail taxCardDetail, PaymentService paymentService) {
        this.order = order;
        this.OSSCardDetail = OSSCardDetail;
        this.taxCardDetail = taxCardDetail;
        this.paymentService = paymentService;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Transferring to TAX - " + order.getTax());
        paymentService.doTransaction("" + System.currentTimeMillis(),
                OSSCardDetail.getCardNumber(),
                OSSCardDetail.getCardExpirationDate(),
                OSSCardDetail.getCardHolderName(),
                OSSCardDetail.getCvv(),
                OSSCardDetail.getZipcode(),
                OSSCardDetail.getCardType(),
                order.getTax(),
                taxCardDetail.getCardNumber(),
                TransactionType.TRANSFER_TAX);
    }
}

package edu.mum.cs490.shoppingcart.springboot.observer;

import edu.mum.cs490.shoppingcart.domain.Order;
//import edu.mum.cs490.shoppingcart.service.IMailService;

import java.util.Observable;
import java.util.Observer;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class MailObserver implements Observer {

    private final Order order;
//    private final IMailService mailService;

    public MailObserver(Order order/*, IMailService mailService*/) {
        this.order = order;
//        this.mailService = mailService;
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Send an email to the receivers");

//        mailService.sendEmailToCustomerAndVendor(order);
    }
}

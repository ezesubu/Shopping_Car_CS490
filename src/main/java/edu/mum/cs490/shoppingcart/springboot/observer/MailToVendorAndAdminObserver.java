package edu.mum.cs490.shoppingcart.springboot.observer;

import edu.mum.cs490.shoppingcart.domain.Vendor;
import edu.mum.cs490.shoppingcart.service.IMailService;

import java.util.Observable;
import java.util.Observer;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class MailToVendorAndAdminObserver implements Observer {

    private final Vendor vendor;
    private final IMailService mailService;

    public MailToVendorAndAdminObserver(Vendor vendor, IMailService mailService) {
        this.vendor = vendor;
        this.mailService = mailService;
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Send an email to the vendor and the admins");

        mailService.sendEmailToVendorAndAdmin(vendor.getEmail(), vendor.getCompanyName());
    }
}

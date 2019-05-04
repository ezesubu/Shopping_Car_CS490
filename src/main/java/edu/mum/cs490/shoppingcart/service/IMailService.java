package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Order;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface IMailService {
    boolean sendEmailToCustomer(String toEmail, String userName);
    boolean sendEmailToVendorAndAdmin(String toEmail, String userName);
    boolean sendEmailToCustomerAndVendor(Order order);
    boolean sendReportToVendor(String toEmail, byte[] report, String nameOfAttachment);
    boolean sendErrorEmailToAdmin(String errorMessage);
}

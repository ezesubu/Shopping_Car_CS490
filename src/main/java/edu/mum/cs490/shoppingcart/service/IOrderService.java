package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Transactional
public interface IOrderService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Order> findAll();

    List<Order> findallEnabledByCustomer_id(Integer customerId);

    List<Order> findByCustomer_id(Integer customerId);

    List<Order> findByVendor_id(Integer vendorId);

    Order findById(Integer orderId);

    Page<Order> findByCustomer_id(Integer customerId, int page);

    List<Order> findByVendor_idBetweenDate(Integer vendorId, Date begin, Date end);

    List<CardDetail> findCardByUser_id(Integer userId);

    CardDetail findCardById(Integer cardId);

    Map<Product, Integer> checkProduct(List<OrderDetail> orderDetails);

    @Transactional
    Order saveOrUpdate(Order order);

    @Transactional
    Integer purchase(Order order);

    String checkProductAvailabilityForCustomer(HttpSession session, Model model, Map<Product, Integer> productUnavailability,
                                    Order order, User user);
    String checkProductAvailabilityForGuest(HttpSession session, Model model, Map<Product, Integer> productUnavailability,
                                    Order order);

    void deductProductQuantityAfterPurchase(Order order);
}

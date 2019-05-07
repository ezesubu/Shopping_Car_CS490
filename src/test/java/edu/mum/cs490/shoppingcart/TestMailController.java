/*
package edu.mum.cs490.shoppingcart;

import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.Guest;
import edu.mum.cs490.shoppingcart.domain.Order;
import edu.mum.cs490.shoppingcart.domain.OrderDetail;
import edu.mum.cs490.shoppingcart.model.ShoppingCart;
import edu.mum.cs490.shoppingcart.model.form.CustomerOrderShippingForm;
import edu.mum.cs490.shoppingcart.model.form.PaymentForm;
import edu.mum.cs490.shoppingcart.service.IMailService;
import edu.mum.cs490.shoppingcart.service.IProductService;
import edu.mum.cs490.shoppingcart.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestMailController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMailService mailService;

    @Test
    public void sendEmailToCustomer() throws Exception {
        String toEmailTrue = "pmshoppingcart2019@gmail.com";
        String userNameTrue = "ShoppingCart Team";
        boolean resultTrue = mailService.sendEmailToCustomer(toEmailTrue, userNameTrue);
        Assert.assertEquals(true, resultTrue);
        String toEmailFalse = "@e";
        String userNameFalse = "";
        boolean resultFalse = mailService.sendEmailToCustomer(toEmailFalse, userNameFalse);
        Assert.assertEquals(false, resultFalse);
    }

    @Test
    public void sendEmailToCustomerAndVendor() throws Exception {
        CustomerOrderShippingForm customerShippingForm = new CustomerOrderShippingForm();
        customerShippingForm.setStreet("1000 North 4th Street");
        customerShippingForm.setCity("Fairfield");
        customerShippingForm.setPhoneNumber("2058871599");
        customerShippingForm.setState("IA");
        customerShippingForm.setZipcode("52557");

        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setCardNumber("49291276512343699");
        paymentForm.setCardType("Visa");
        paymentForm.setCardHolderName("yee rick");
        paymentForm.setCvv("123");
        paymentForm.setCardZipcode("52557");
        String month = "05";
        String year = "2019";

        ShoppingCart sc = new ShoppingCart();
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail od = new OrderDetail();
        od.setProduct(productService.getOne(1));
        od.setQuantity(1);
        od.setPrice(150);
        orderDetails.add(od);
        sc.setOrderDetails(orderDetails);

        Order order = new Order();
        order.setOrderDetails(sc.getOrderDetails());
        boolean resultTrue =mailService.sendEmailToCustomerAndVendor(order);
        Assert.assertEquals(false, resultTrue);

        Customer customerFalse = new Customer();
        customerFalse.setEmail("pmshoppingcart2019@gmail.com");
        order.setCustomer(customerFalse);
        boolean resultFalse = mailService.sendEmailToCustomerAndVendor(order);
        Assert.assertEquals(true, resultFalse);
    }
}
*/

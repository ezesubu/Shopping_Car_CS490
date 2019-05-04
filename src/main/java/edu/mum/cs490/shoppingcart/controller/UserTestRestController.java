package edu.mum.cs490.shoppingcart.controller;

import edu.mum.cs490.shoppingcart.domain.Admin;
import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.User;
import edu.mum.cs490.shoppingcart.domain.Vendor;
import edu.mum.cs490.shoppingcart.service.IAdminService;
import edu.mum.cs490.shoppingcart.service.ICustomerService;
import edu.mum.cs490.shoppingcart.service.IUserService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@RestController
public class UserTestRestController {

    private final IAdminService adminService;
    private final IVendorService vendorService;
    private final ICustomerService customerService;
    private final IUserService userService;

    @Autowired
    public UserTestRestController(IAdminService adminService, IVendorService vendorService, ICustomerService customerService, IUserService<User> userService) {
        this.adminService = adminService;
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.userService = userService;
    }


    @RequestMapping("admin/all")
    public List<Admin> getAdmins() {
        return adminService.getAll();
    }

    @RequestMapping("vendor/all")
    public List<Vendor> getVendors() {
        return vendorService.getAll();
    }

    @RequestMapping("customer/all")
    public List<Customer> getCustomers() {
        return customerService.getAll();
    }

    @RequestMapping("user/all")
    public List<User> getUsers() {
        return userService.getAll();
    }

}

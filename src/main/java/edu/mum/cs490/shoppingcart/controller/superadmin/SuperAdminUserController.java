/*
 * Created by: Ezesubu
 * Date: April 20, 2019
 * Name: View
 * DependingIn:  Boostrap3.6.->
 * Description: UI Template
 * Module: UI Template
 */

package edu.mum.cs490.shoppingcart.controller.superadmin;

import edu.mum.cs490.shoppingcart.domain.*;
import edu.mum.cs490.shoppingcart.model.Message;
import edu.mum.cs490.shoppingcart.model.form.user.*;
import edu.mum.cs490.shoppingcart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Controller
@RequestMapping(value = {"superadmin/user", "superadmin/u"})
public class SuperAdminUserController {

    private final ISuperAdminService superAdminService;
    private final IUserService userService;
    private final ICustomerService customerService;
    private final IVendorService vendorService;
    private final PasswordEncoder passwordEncoder;

    private final int PAGE_SIZE = 10;

    @Autowired
    public SuperAdminUserController(ISuperAdminService superAdminService, IUserService userService, ICustomerService customerService, IVendorService vendorService, PasswordEncoder passwordEncoder) {
        this.superAdminService = superAdminService;
        this.userService = userService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"sa", "superadmin"})
    public String index(Model model) {
        model.addAttribute("statuses", Status.values());
        getAdmins(null, null, null, Status.ENABLED,1, model);
        return "superadmin/user/admin/index";
    }

    @RequestMapping(value = {"sa/l", "superadmin/list"})
    public String getAdmins(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                            @RequestParam(defaultValue = "1") Integer page,
                            Model model) {
        Page<SuperAdmin> sadminList =  superAdminService.findPage(username, firstName,lastName, status, PageRequest.of(page-1,PAGE_SIZE));
        model.addAttribute("list", sadminList.getContent());
        model.addAttribute("result", sadminList);
        //model.addAttribute("list", adminService.find(username, firstName, lastName, status));
        return "superadmin/user/admin/list";
    }

    @GetMapping(value = {"sa/e", "superadmin/edit"})
    public String editAdmin(@RequestParam Integer id, Model model) {
        SuperAdmin sadmin = superAdminService.getById(id);
        if (sadmin == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("superadminForm", new SuperAdminForm(sadmin));
        return "superadmin/user/admin/edit";
    }

    @PostMapping(value = {"sa/e", "superadmin/edit"})
    public String editSuperAdmin(@Valid @ModelAttribute("superadminForm") SuperAdminForm superadminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "superadmin/user/admin/edit";
        }
        if (superAdminService.existByIdNotAndUsername(superadminForm.getId(), superadminForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "superadmin/user/admin/edit";
        }
        SuperAdmin sadmin = superAdminService.getById(superadminForm.getId());
        sadmin.setEmail(superadminForm.getEmail());
        sadmin.setUsername(superadminForm.getUsername());
        sadmin.setLastName(superadminForm.getLastName());
        sadmin.setFirstName(superadminForm.getFirstName());
        superAdminService.saveOrUpdate(sadmin);
        model.addAttribute("message", Message.successfullySaved);
        return "superadmin/user/admin/edit";
    }

    @GetMapping(value = {"sa/c", "superadmin/create"})
    public String createSuperAdmin(Model model) {
        model.addAttribute("superadminForm", new SuperAdminSignUpForm());
        return "superadmin/user/admin/create";
    }

    @PostMapping(value = {"sa/c", "superadmin/create"})
    public String createSuperAdmin(@Valid @ModelAttribute("superadminForm") SuperAdminSignUpForm superadminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "superadmin/user/admin/create";
        }
        if (superAdminService.getByUsername(superadminForm.getUsername()) != null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "superadmin/user/admin/create";
        }
        SuperAdmin sadmin = new SuperAdmin();
        sadmin.setPassword(passwordEncoder.encode(superadminForm.getPassword()));
        sadmin.setEmail(superadminForm.getEmail());
        sadmin.setUsername(superadminForm.getUsername());
        sadmin.setLastName(superadminForm.getLastName());
        sadmin.setFirstName(superadminForm.getFirstName());
        superAdminService.saveOrUpdate(sadmin);
        model.addAttribute("message", Message.successfullySaved);
        return "superadmin/user/admin/create";
    }

    @RequestMapping(value = {"v", "vendor"})
    public String getVendors(Model model) {
        model.addAttribute("statuses", Status.values());
        return "superadmin/user/vendor/index";
    }

    @RequestMapping(value = {"v/l", "vendor/list"})
    public String getAdmins(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String companyName,
                            @RequestParam(required = false) Status status,
                            @RequestParam(defaultValue = "1") Integer page,
                            Model model) {

        Page<Vendor> vendorList = vendorService.findPage(username, companyName, status, PageRequest.of(page - 1, PAGE_SIZE));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", vendorList.getContent());
        model.addAttribute("result", vendorList);
        return "superadmin/user/vendor/list";
    }

    @GetMapping(value = {"v/e", "vendor/edit"})
    public String editVendor(@RequestParam Integer id, Model model) {
        Vendor vendor = vendorService.getById(id);
        if (vendor == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("vendorForm", new VendorForm(vendor));
        return "superadmin/user/vendor/edit";
    }

    @PostMapping(value = {"v/e", "vendor/edit"})
    public String editVendor(@Valid @ModelAttribute("vendorForm") VendorForm vendorForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "superadmin/user/vendor/edit";
        }
        if (vendorService.existByIdNotAndUsername(vendorForm.getId(), vendorForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "superadmin/user/vendor/edit";
        }
        Vendor vendor = vendorService.getById(vendorForm.getId());
        vendor.setEmail(vendorForm.getEmail());
        vendor.setUsername(vendorForm.getUsername());
        vendor.setCompanyName(vendorForm.getCompanyName());
        vendorService.saveOrUpdate(vendor);
        model.addAttribute("message", Message.successfullySaved);
        return "superadmin/user/vendor/edit";
    }

    @RequestMapping(value = {"c", "customer"})
    public String getCustomers(Model model) {
        model.addAttribute("statuses", Status.values());
        return "superadmin/user/customer/index";
    }

    @RequestMapping(value = {"c/l", "customer/list"})
    public String getCustomers(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                               @RequestParam(defaultValue = "1") Integer page,
                               Model model) {
        Page<Customer> customerList = customerService.findPage(username, firstName, lastName, status, PageRequest.of(page - 1, PAGE_SIZE));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", customerList.getContent());
        model.addAttribute("result", customerList);
        //model.addAttribute("list", customerService.find(username, firstName, lastName, status));
        return "superadmin/user/customer/list";
    }

    @GetMapping(value = {"c/e", "customer/edit"})
    public String editCustomer(@RequestParam Integer id, Model model) {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("customerForm", new CustomerForm(customer));
        return "superadmin/user/customer/edit";
    }

    @PostMapping(value = {"c/e", "customer/edit"})
    public String editCustomer(@Valid @ModelAttribute("customerForm") CustomerForm customerForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "superadmin/user/customer/edit";
        }
        if (customerService.existByIdNotAndUsername(customerForm.getId(), customerForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "superadmin/user/customer/edit";
        }
        Customer customer = customerService.getById(customerForm.getId());
        customer.setEmail(customerForm.getEmail());
        customer.setUsername(customerForm.getUsername());
        customer.setLastName(customerForm.getLastName());
        customer.setFirstName(customerForm.getFirstName());
        customerService.saveOrUpdate(customer);
        model.addAttribute("message", Message.successfullySaved);
        return "superadmin/user/customer/edit";
    }

    @RequestMapping(value = {"d", "delete"})
    @ResponseBody
    public Message delete(@RequestParam Integer id,
                          Model model) {
        userService.delete(id);
//        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));
        return new Message(Message.Type.SUCCESS, "successfully.deleted");
    }

    @RequestMapping(value = {"changeStatus"})
    @ResponseBody
    public Message changeStatus(@RequestParam Integer id, @RequestParam Status status,
                                Model model) {
        userService.changeStatus(id, status);
//        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));
        return new Message(Message.Type.SUCCESS, "successfully.changed.status");
    }

}

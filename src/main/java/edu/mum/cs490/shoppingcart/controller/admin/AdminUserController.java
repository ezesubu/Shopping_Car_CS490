package edu.mum.cs490.shoppingcart.controller.admin;

import edu.mum.cs490.shoppingcart.domain.Admin;
import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.domain.Vendor;
import edu.mum.cs490.shoppingcart.model.Message;
import edu.mum.cs490.shoppingcart.model.form.user.AdminForm;
import edu.mum.cs490.shoppingcart.model.form.user.AdminSignUpForm;
import edu.mum.cs490.shoppingcart.model.form.user.CustomerForm;
import edu.mum.cs490.shoppingcart.model.form.user.VendorForm;
import edu.mum.cs490.shoppingcart.service.IAdminService;
import edu.mum.cs490.shoppingcart.service.ICustomerService;
import edu.mum.cs490.shoppingcart.service.IUserService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
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
@RequestMapping(value = {"admin/user", "admin/u"})
public class AdminUserController {

    private final IAdminService adminService;
    private final IUserService userService;
    private final ICustomerService customerService;
    private final IVendorService vendorService;
    private final PasswordEncoder passwordEncoder;

    private final int PAGE_SIZE = 10;

    @Autowired
    public AdminUserController(IAdminService adminService, IUserService userService, ICustomerService customerService, IVendorService vendorService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.userService = userService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"a", "admin"})
    public String index(Model model) {
        model.addAttribute("statuses", Status.values());
        getAdmins(null, null, null, Status.ENABLED,1, model);
        return "admin/user/admin/index";
    }

    @RequestMapping(value = {"a/l", "admin/list"})
    public String getAdmins(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                            @RequestParam(defaultValue = "1") Integer page,
                            Model model) {
        Page<Admin> adminList =  adminService.findPage(username, firstName,lastName, status, PageRequest.of(page-1,PAGE_SIZE));
        model.addAttribute("list", adminList.getContent());
        model.addAttribute("result", adminList);
        //model.addAttribute("list", adminService.find(username, firstName, lastName, status));
        return "admin/user/admin/list";
    }

    @GetMapping(value = {"a/e", "admin/edit"})
    public String editAdmin(@RequestParam Integer id, Model model) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("adminForm", new AdminForm(admin));
        return "admin/user/admin/edit";
    }

    @PostMapping(value = {"a/e", "admin/edit"})
    public String editAdmin(@Valid @ModelAttribute("adminForm") AdminForm adminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/admin/edit";
        }
        if (adminService.existByIdNotAndUsername(adminForm.getId(), adminForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/admin/edit";
        }
        Admin admin = adminService.getById(adminForm.getId());
        admin.setEmail(adminForm.getEmail());
        admin.setUsername(adminForm.getUsername());
        admin.setLastName(adminForm.getLastName());
        admin.setFirstName(adminForm.getFirstName());
        adminService.saveOrUpdate(admin);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/admin/edit";
    }

    @GetMapping(value = {"a/c", "admin/create"})
    public String createAdmin(Model model) {
        model.addAttribute("adminForm", new AdminSignUpForm());
        return "admin/user/admin/create";
    }

    @PostMapping(value = {"a/c", "admin/create"})
    public String createAdmin(@Valid @ModelAttribute("adminForm") AdminSignUpForm adminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/admin/create";
        }
        if (adminService.getByUsername(adminForm.getUsername()) != null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/admin/create";
        }
        Admin admin = new Admin();
        admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
        admin.setEmail(adminForm.getEmail());
        admin.setUsername(adminForm.getUsername());
        admin.setLastName(adminForm.getLastName());
        admin.setFirstName(adminForm.getFirstName());
        adminService.saveOrUpdate(admin);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/admin/create";
    }

    @RequestMapping(value = {"v", "vendor"})
    public String getVendors(Model model) {
        model.addAttribute("statuses", Status.values());
        return "admin/user/vendor/index";
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
        return "admin/user/vendor/list";
    }

    @GetMapping(value = {"v/e", "vendor/edit"})
    public String editVendor(@RequestParam Integer id, Model model) {
        Vendor vendor = vendorService.getById(id);
        if (vendor == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("vendorForm", new VendorForm(vendor));
        return "admin/user/vendor/edit";
    }

    @PostMapping(value = {"v/e", "vendor/edit"})
    public String editVendor(@Valid @ModelAttribute("vendorForm") VendorForm vendorForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/vendor/edit";
        }
        if (vendorService.existByIdNotAndUsername(vendorForm.getId(), vendorForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/vendor/edit";
        }
        Vendor vendor = vendorService.getById(vendorForm.getId());
        vendor.setEmail(vendorForm.getEmail());
        vendor.setUsername(vendorForm.getUsername());
        vendor.setCompanyName(vendorForm.getCompanyName());
        vendorService.saveOrUpdate(vendor);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/vendor/edit";
    }

    @RequestMapping(value = {"c", "customer"})
    public String getCustomers(Model model) {
        model.addAttribute("statuses", Status.values());
        return "admin/user/customer/index";
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
        return "admin/user/customer/list";
    }

    @GetMapping(value = {"c/e", "customer/edit"})
    public String editCustomer(@RequestParam Integer id, Model model) {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "user.not.found"));
        }
        model.addAttribute("customerForm", new CustomerForm(customer));
        return "admin/user/customer/edit";
    }

    @PostMapping(value = {"c/e", "customer/edit"})
    public String editCustomer(@Valid @ModelAttribute("customerForm") CustomerForm customerForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/customer/edit";
        }
        if (customerService.existByIdNotAndUsername(customerForm.getId(), customerForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/customer/edit";
        }
        Customer customer = customerService.getById(customerForm.getId());
        customer.setEmail(customerForm.getEmail());
        customer.setUsername(customerForm.getUsername());
        customer.setLastName(customerForm.getLastName());
        customer.setFirstName(customerForm.getFirstName());
        customerService.saveOrUpdate(customer);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/customer/edit";
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

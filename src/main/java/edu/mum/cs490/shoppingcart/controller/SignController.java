package edu.mum.cs490.shoppingcart.controller;

import edu.mum.cs490.shoppingcart.domain.*;
import edu.mum.cs490.shoppingcart.model.Message;
import edu.mum.cs490.shoppingcart.model.form.user.*;
//import edu.mum.cs490.shoppingcart.service.IMailService;
import edu.mum.cs490.shoppingcart.service.IEmailService;
import edu.mum.cs490.shoppingcart.service.IUserService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
import edu.mum.cs490.shoppingcart.service.impl.FileManagementService;
import edu.mum.cs490.shoppingcart.utility.AESConverterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Controller
@SuppressWarnings("unchecked")
public class SignController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
//    private final IMailService mailService;
    private final IVendorService vendorService;
    private final FileManagementService fileManagementService;
    private final AESConverterUtility aesConverter;

    @Autowired
    IEmailService emailServiceInterface;

    @Autowired
    public SignController(IUserService userService, PasswordEncoder passwordEncoder /*, IMailService mailService*/, IVendorService vendorService, FileManagementService fileManagementService, AESConverterUtility aesConverter) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
//        this.mailService = mailService;
        this.vendorService = vendorService;
        this.fileManagementService = fileManagementService;
        this.aesConverter = aesConverter;
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signUp(@AuthenticationPrincipal User user, ModelMap model) {
        if (user != null) {
            return "redirect://";
        }
        model.put("moduleForm", new CustomerSignUpForm());
        return "signUp";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("moduleForm") CustomerSignUpForm userForm, BindingResult error, ModelMap model) throws IOException, MessagingException {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "signUp";
        }

        if (userService.getByUsername(userForm.getUsername()) != null) {
            error.rejectValue("username", "username.duplicate");
            return "signUp";
        }

        Customer customer = new Customer();
        customer.setStatus(Status.ENABLED);
        setToUser(customer, userForm);

        customer.setFirstName(userForm.getFirstName());
        customer.setLastName(userForm.getLastName());

        userService.saveOrUpdate(customer);
        emailServiceInterface.sendEmail(userForm.getEmail(), "User sign up", customer.getFirstName() + " " + customer.getLastName());
//        mailService.sendEmailToCustomer(userForm.getEmail(), customer.getFirstName() + " " + customer.getLastName());
        model.put("message", Message.successfullySaved);
        return "signUp";
    }

    @RequestMapping(value = "vendor/signup", method = RequestMethod.GET)
    public String vendorSignUp(@AuthenticationPrincipal User user, ModelMap model) {
        if (user != null) {
            return "redirect://";
        }
        model.put("moduleForm", new VendorSignUpForm());
        return "vendor/signUp";
    }

    @RequestMapping(value = "vendor/signup", method = RequestMethod.POST)
    public String vendorSignUp(@Valid @ModelAttribute("moduleForm") VendorSignUpForm userForm, BindingResult error,
                               HttpServletRequest request,
                               ModelMap model) {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "vendor/signUp";
        }

        if (userForm.getFile().isEmpty()) {
            error.rejectValue("file", null,"must not be empty");
            return "vendor/signUp";
        }

        if (userService.getByUsername(userForm.getUsername()) != null) {
            error.rejectValue("username", "username.duplicate");
            return "vendor/signUp";
        }

        if (vendorService.getByCompanyName(userForm.getCompanyName()) != null) {
            error.rejectValue("companyName", null, "Company name exists");
            return "vendor/signUp";
        }

        if (!userForm.getFile().isEmpty() && !fileManagementService.checkImageExtension(userForm.getFile().getOriginalFilename())) {
            error.rejectValue("companyName", null, "File extension must be .jpg or .png!");
            return "vendor/signUp";
        }

        //check for paymentform validation error
        if (request.getParameter("month") != null && request.getParameter("year") != null) {
            userForm.setCardExpirationDate(request.getParameter("month") + "/" + request.getParameter("year"));
        }

        Vendor vendor = new Vendor();
        setToUser(vendor, userForm);

        vendor.setCompanyName(userForm.getCompanyName());

        CardDetail cardDetail = new CardDetail();
        setCardDetail(cardDetail, userForm);
        List<CardDetail> list = new ArrayList<CardDetail>();
        list.add(cardDetail);
        vendor.setCards(list);
        cardDetail.setOwner(vendor);

        userService.saveOrUpdate(vendor);

        String fileFullName = fileManagementService.createFile(userForm.getFile(), "vendor", vendor.getId());

        if (fileFullName != null) {
            vendor.setImage(fileFullName);
            userService.saveOrUpdate(vendor);
            model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.created"));
        }

        vendorService.transferFee(cardDetail, vendor);
        model.put("message", Message.successfullySaved);
        return "vendor/signUp";
    }

    private void setToUser(User user, UserSignUpForm form) {
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
    }

    private void setCardDetail(CardDetail cardDetail, VendorSignUpForm form) {
        cardDetail.setCardType(form.getCardType());
        cardDetail.setCardHolderName(aesConverter.encrypt(form.getCardHolderName().toUpperCase()));
        cardDetail.setCardNumber(aesConverter.encrypt(form.getCardNumber().replaceAll("\\s", "")));
        cardDetail.setCardExpirationDate(aesConverter.encrypt(form.getCardExpirationDate()));
        cardDetail.setCvv(aesConverter.encrypt(form.getCvv()));
        cardDetail.setZipcode(aesConverter.encrypt(form.getCardZipcode()));
        cardDetail.setLast4Digit(form.getCardNumber().substring(form.getCardNumber().length() - 4));
    }
}

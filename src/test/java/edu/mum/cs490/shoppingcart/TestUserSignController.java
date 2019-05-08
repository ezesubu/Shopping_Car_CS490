package edu.mum.cs490.shoppingcart;

import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.Vendor;
import edu.mum.cs490.shoppingcart.model.Message;
import edu.mum.cs490.shoppingcart.model.form.user.CustomerSignUpForm;
import edu.mum.cs490.shoppingcart.model.form.user.VendorSignUpForm;
import edu.mum.cs490.shoppingcart.service.ICustomerService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Thomas Tibebu on 5/03/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserSignController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkforwardedUrl() throws Exception {

        mockMvc.perform(get("/login")).andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
        mockMvc.perform(get("/vendor/signup")).andExpect(forwardedUrl("/WEB-INF/jsp/vendor/signUp.jsp"));
        mockMvc.perform(get("/signup")).andExpect(forwardedUrl("/WEB-INF/jsp/signUp.jsp"));
//        Admin test = (Admin) userService.saveOrUpdate(admin);

    }

    @Test
//    @WithMockUser(username="admin",roles={"CUSTOMER","VENDOR", "ADMIN"})
    public void checkExistingUser() throws Exception {

        CustomerSignUpForm signUpForm = new CustomerSignUpForm();
        VendorSignUpForm vendorSignUpForm = new VendorSignUpForm();

        signUpForm.setFirstName("thomas");
        signUpForm.setLastName("tibebu");
        signUpForm.setPassword("best1212pass");
        signUpForm.setRePassword("best1212pass");
        signUpForm.setUsername("ttibebu2019");
        signUpForm.setEmail("tomnetgeneraltradingplc@gmail.com");

        vendorSignUpForm.setCompanyName("Erdenebayar");
        vendorSignUpForm.setPassword("hello");
        vendorSignUpForm.setRePassword("hello");
        vendorSignUpForm.setUsername("erdenebayar");
        vendorSignUpForm.setEmail("ebatsukh@mum.edu");

        Path path = Paths.get("D:\\resources\\product\\4\\4.png");
        String name = "4.png";
        String originalFileName = "4.png";
        String contentType = "jpg;png";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        vendorSignUpForm.setFile(result);
        vendorSignUpForm.setCardNumber("4000300020003002");
        vendorSignUpForm.setCardType("MASTER");
        vendorSignUpForm.setCvv("302");
        vendorSignUpForm.setCardExpirationDate("05/20");
        vendorSignUpForm.setCardHolderName("V2");
        vendorSignUpForm.setCardZipcode("52557");

        // Check User Signup to controller
        mockMvc.perform(post("/signup").flashAttr("moduleForm", signUpForm))
                .andExpect(model().attributeHasFieldErrors("moduleForm", "username"));
        //moduleForm is from SignController

        // Check Vendor Signup
        mockMvc.perform(post("/vendor/signup").flashAttr("moduleForm", vendorSignUpForm))
                .andExpect(model().attributeHasFieldErrors("moduleForm", "username"));
    }

    @Test
    public void isSavingToDatabase() throws Exception {

        CustomerSignUpForm signUpForm = new CustomerSignUpForm();
        VendorSignUpForm vendorSignUpForm = new VendorSignUpForm();

        signUpForm.setFirstName("Erdenebayar");
        signUpForm.setLastName("Batsukh");
        signUpForm.setPassword("hello");
        signUpForm.setRePassword("hello");
        signUpForm.setUsername("uniqueUser1");
        signUpForm.setEmail("ebatsukh@mum.edu");

        vendorSignUpForm.setCompanyName("Erdenebayar");
        vendorSignUpForm.setPassword("hello");
        vendorSignUpForm.setRePassword("hello");
        vendorSignUpForm.setUsername("uniqueUser2");
        vendorSignUpForm.setEmail("ebatsukh@mum.edu");

        Path path = Paths.get("D:\\resources\\product\\4\\4.png");
        String name = "4.png";
        String originalFileName = "4.png";
        String contentType = "jpg;png";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        vendorSignUpForm.setFile(result);
        vendorSignUpForm.setCardNumber("4000300020003002");
        vendorSignUpForm.setCardType("MASTER");
        vendorSignUpForm.setCvv("302");
        vendorSignUpForm.setCardExpirationDate("05/20");
        vendorSignUpForm.setCardHolderName("V2");
        vendorSignUpForm.setCardZipcode("52557");

        mockMvc.perform(post("/signup").flashAttr("moduleForm", signUpForm))
                .andExpect(model().attribute("message", Message.successfullySaved));

        mockMvc.perform(post("/vendor/signup").flashAttr("moduleForm", vendorSignUpForm))
                .andExpect(model().attribute("message", Message.successfullySaved));


        Customer customers = (Customer) customerService.getByUsername("uniqueUser1");
        Vendor vendors = (Vendor) vendorService.getByUsername("uniqueUser2");

        Assert.assertNotNull(customers);
        Assert.assertNotNull(vendors);
    }
}

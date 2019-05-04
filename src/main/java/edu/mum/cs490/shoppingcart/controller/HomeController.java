package edu.mum.cs490.shoppingcart.controller;

import edu.mum.cs490.shoppingcart.domain.Category;
import edu.mum.cs490.shoppingcart.domain.Product;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.domain.Vendor;
import edu.mum.cs490.shoppingcart.service.ICategoryService;
import edu.mum.cs490.shoppingcart.service.IProductService;
import edu.mum.cs490.shoppingcart.service.IUserService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Controller
public class HomeController {

    public final IUserService userService;

    @Autowired
    public HomeController(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;
    @Autowired
    private IVendorService vendorService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/")
    public String getHome(Model model){


        List<Product> productList = productService.find(null, null, null, Status.ENABLED, null);
        List<Category> mainCategories = categoryService.find(null, null, Status.ENABLED);
        List<Category> categories = categoryService.findAllActiveInList();
        List<Vendor> vendors = vendorService.find(null, null, Status.ENABLED);

        productList.addAll(productList);
        model.addAttribute("products", productList);
        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", categories);

        servletContext.setAttribute("mainCategories", mainCategories);

        return "index";
    }

    @RequestMapping("/contact")
    public String getContact(){
        return "contact";
    }

}

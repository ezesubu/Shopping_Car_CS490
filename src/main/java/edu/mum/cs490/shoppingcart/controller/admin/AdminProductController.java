package edu.mum.cs490.shoppingcart.controller.admin;


import edu.mum.cs490.shoppingcart.domain.Product;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.model.Message;
import edu.mum.cs490.shoppingcart.model.form.ProductForm;
import edu.mum.cs490.shoppingcart.service.ICategoryService;
import edu.mum.cs490.shoppingcart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private Path path;

    private final IProductService productService;

    private final ICategoryService categoryService;

    @Autowired
    public AdminProductController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String productManagement(Model model) {
        List<Product> productsList = productService.find(null, null, null, Status.ENABLED, null);
        model.addAttribute("productList", productsList);
        model.addAttribute("categories", categoryService.find(null, null, null));
        model.addAttribute("statuses", Status.values());

        return "admin/product/index";
    }

    @RequestMapping(value = "/list")
    public String getProduct(@RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer categoryId,
                             @RequestParam(required = false , defaultValue = "ENABLED") Status status,
                             Model model) {

        List<Product> productsList = productService.find(name.equals("") ? null : name, categoryId, null, status, null);
        model.addAttribute("productList", productsList);
        model.addAttribute("statuses", Status.values());

        return "/admin/product/list";
    }


    @GetMapping("/update")
    public String saveOrUpdate(@RequestParam(required = false) Integer id,
                               @RequestParam(required = false) Status status,
                               Model model) {



        if (id != null && id != 0) {
            model.addAttribute("productForm", new ProductForm(productService.getOne(id)));
            model.addAttribute("title", "Update Product");
        } else {
            model.addAttribute("productForm", new ProductForm());
            model.addAttribute("title", "Create New Product");
        }
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        model.addAttribute("statuses", Status.values());
        return "admin/product/edit";
    }

    @PostMapping("/update")
    public String saveOrUpdate(@Valid @ModelAttribute("productForm") ProductForm form, BindingResult result,
                               @RequestParam("status") Status status, Model model) {

        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));

        if (result.hasErrors()) {
            model.addAttribute("message", new Message(Message.Type.ERROR, "Check your forms!!!"));
            return "admin/saveProduct";
        }

        Product product = productService.getOne(form.getId());

        product.setStatus(status);
        product.setCategory(categoryService.getCategoryById(form.getCategoryId()));
        product.setDescription(form.getDescription());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setQuantity(form.getQuantity());
        productService.saveOrUpdate(product);
        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.saved"));

        return "admin/product/edit";
    }

    @GetMapping("/delete")
    @ResponseBody
    public Message deleteProduct(@RequestParam(required = true) Integer id) {
        productService.delete(id);
        return new Message(Message.Type.SUCCESS, "successfully.deleted");
    }

    @RequestMapping(value = {"changeStatus"})
    @ResponseBody
    public Message changeStatus(@RequestParam Integer id, @RequestParam Status status,
                                Model model) {
        productService.changeStatus(id, status);
        return new Message(Message.Type.SUCCESS, "successfully.changed.status");
    }
}

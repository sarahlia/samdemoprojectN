package com.example.samdemoproject.controllers;

import com.example.samdemoproject.daos.ProductRepository;
import com.example.samdemoproject.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    //dependency injection
    private ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public String viewAllProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "product/viewAll";
    }

    @GetMapping("/product/create")
    public String showCreateForm() {
        return "product/create";
    }

    @PostMapping("/product/create")
    public String saveCreateForm(
         @RequestParam(name = "name") String name,
         @RequestParam(name = "description") String description,
         @RequestParam(name = "price") double price,
         @RequestParam(name = "image") String image
    ) {
        Product productToAdd = new Product(name, description, price, image);
        Product productInDB = productDao.save(productToAdd);

        return "redirect:/products";
    }
}

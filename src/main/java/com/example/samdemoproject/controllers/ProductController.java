package com.example.samdemoproject.controllers;

import com.example.samdemoproject.daos.ProductRepository;
import com.example.samdemoproject.daos.UserRepository;
import com.example.samdemoproject.models.Product;
import com.example.samdemoproject.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    //dependency injection
    private ProductRepository productDao;
    private UserRepository userDao;

    public ProductController(ProductRepository productDao, UserRepository userDao) {
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @GetMapping("/products.json")
    public @ResponseBody List<Product> viewProductsInJSON() {
        return productDao.findAll();
    }

    @GetMapping("/products")
    public String viewAllProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "product/viewAll";
    }

    @GetMapping("/product/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/product/create")
    public String saveCreateForm(@ModelAttribute Product productToAdd) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productToAdd.setUser(currentUser);
        Product productInDB = productDao.save(productToAdd);
        //add emailService later
        return "redirect:/product/" + productInDB.getId();
    }

    @GetMapping("/product/{id}")
    public String viewAProduct(@PathVariable long id, Model model) {
        Product productToView = productDao.getOne(id);
        model.addAttribute("product", productToView);
        return "product/one";
    }

    @GetMapping("/product/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Product productToEdit = productDao.getOne(id);
        model.addAttribute("product", productToEdit);
        return "product/edit";
    }

    @PostMapping("/product/{id}/edit")
    public String updateForm(@ModelAttribute Product productToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productToEdit.setUser(currentUser);

        //edit the product
//        productToEdit.setName(name);
//        productToEdit.setDescription(description);
//        productToEdit.setPrice(price);
//        productToEdit.setImg(image);

        //save the changes
        productDao.save(productToEdit);
        return "redirect:/product/" + productToEdit.getId();
    }

//    @GetMapping("/product/{id}/delete")
//    public String showDeleteForm(@PathVariable long id, Model model) {
//        Product productToDelete = productDao.getOne(id);
//        model.addAttribute("product", productToDelete);
//        return "product/delete";
//    }

    @PostMapping("/product/{id}/delete")
    public String destroy(@PathVariable long id) {
        productDao.deleteById(id);
        return "redirect:/products";
    }
}

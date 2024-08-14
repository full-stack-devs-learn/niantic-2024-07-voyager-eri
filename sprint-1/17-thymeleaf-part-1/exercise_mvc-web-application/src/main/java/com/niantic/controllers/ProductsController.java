package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ProductsController
{
    ProductDao productsDao = new ProductDao();
    CategoryDao categoriesDao = new CategoryDao();

    @GetMapping("/products")
    public String getProductsByCategory(Model model, Integer catId)
    {
        ArrayList<Product> products;
        Category category;

        if(catId == null)
        {
            products = productsDao.getProductsByCategory(1);
            category = categoriesDao.getCategoryById(1);
        }
        else
        {
            products = productsDao.getProductsByCategory(catId);
            category = categoriesDao.getCategoryById(catId);
        }

        StringBuilder builder = new StringBuilder();

        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String details(Model model, @PathVariable int id)
    {
        var product = productsDao.getProduct(id);
        Category category = categoriesDao.getCategoryById(id);

        model.addAttribute("product", product);
        model.addAttribute("category", category);

        return "products/details";
    }
}

package com.niantic.controllers;

import com.niantic.models.Product;
import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ProductsController
{
    ProductDao productsDao = new ProductDao();

    @GetMapping("/products")
    public String getProductsByCategory(Model model, Integer categoryId)
    {
        ArrayList<Product> products;

        if(categoryId == null)
        {
            products = productsDao.getProductsByCategory(0);
        }
        else
        {
            products = productsDao.getProductsByCategory(categoryId);
        }

        StringBuilder builder = new StringBuilder();

        model.addAttribute("products", products);
        return "products/index";

    }
}
